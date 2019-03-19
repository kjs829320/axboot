var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: "GET",
            url: ["commonCodes2"],
            data: caller.searchView.getData(),
            callback: function (res) {
            	console.log("res");
            	console.log(res);
                caller.gridView01.setData(res);
            }
        });
        return false;
    },
    DETAIL_SEARCH: function (caller, act, data) {
    	var params = caller.searchView.getData();
    		params.codeType = "D";
    		if(data){
    			params.groupCd = data.groupCd;
    		}
    	//console.log("params");
    	//console.log(params);
    	axboot.ajax({
            type: "GET",
            url: ["commonCodes2"],
            data: params,
            //data: caller.searchView.getData(),
            callback: function (res) {
                caller.gridView02.setData(res);
            }
        });
        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        var saveList = [].concat(caller.gridView02.getData("modified"));
        saveList = saveList.concat(caller.gridView02.getData("deleted"));

        axboot.ajax({
            type: "PUT",
            url: ["commonCodes2"],
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push(LANG("onsave"));
            }
        });
    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView02.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView02.delRow("selected");
    }
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();
    this.gridView02.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
    ACTIONS.dispatch(ACTIONS.DETAIL_SEARCH);
};

fnObj.pageResize = function () {

};


fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, "data-page-btn", {
            "search": function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            "save": function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            }
        });
    }
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document["searchView0"]);
        this.target.attr("onsubmit", "return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);");
        this.filter = $("#filter");
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val()
        }
    }
});


/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;
        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            sortable: true,
            multipleSelect: false,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                {key: "groupCd", label: COL("ax.admin.commoncode.group.code"), width: 150, align: "center", editor: {type: "text", disabled: "notCreated"}},
                {key: "groupNm", label: COL("ax.admin.commoncode.group.name"), width: 150, align: "center", editor: {type: "text", disabled: "notCreated"}}
            ],
            body: {
                onClick: function () {
                    //this.self.select(this.dindex, {selectedClear: true});
                	ACTIONS.dispatch(ACTIONS.DETAIL_SEARCH, this.item);
                },
                onDBLClick: function(){
                	/*console.log("onDBLClick");
                    console.log(this);*/
                    // If change Data
                },
                onDataChanged: function(){
                	//alert(3);
                	/*console.log("onDataChanged");
                    console.log(this);*/
                    // If change Data
                }
            }
        });
        axboot.buttonClick(this, "data-grid-view-01-btn", {
            "add": function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            "delete": function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            }
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == "modified" || _type == "deleted") {
            list = ax5.util.filter(_list, function () {
                return this.groupCd && this.code;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({__created__: true, posUseYn: "N", useYn: "Y"}, "last");
    }
});

/**
 * gridView
 */
fnObj.gridView02 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;
        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            sortable: true,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-02"]'),
            columns: [
                {key: "groupCd", label: COL("ax.admin.commoncode.group.code"), width: 250, align: "center", editor: {type: "text", disabled: "notCreated"}},
                {key: "groupNm", label: COL("ax.admin.commoncode.group.name"), width: 200, align: "center", editor: "text"},
                {key: "code", label: COL("ax.admin.commoncode.code"), width: 100, align: "center", editor: {type: "text", disabled: "notCreated"}},
                {key: "name", label: COL("ax.admin.commoncode.name"), width: 150, align: "left", editor: "text"},
                {key: "sort", label: COL("ax.admin.sort"), editor: "number"},
                {key: "useYn", label: COL("ax.admin.use.or.not"), editor: "checkYn"},
                {key: "remark", label: COL("ax.admin.remark"), width: 200, align: "left", editor: "text"},
                {key: "data1", label: COL("ax.admin.commoncode.data1"), width: 70, align: "left", editor: "text"},
                {key: "data2", label: COL("ax.admin.commoncode.data2"), width: 70, align: "left", editor: "text"},
                {key: "data3", label: COL("ax.admin.commoncode.data3"), width: 70, align: "left", editor: "text"},
                {key: "data4", label: COL("ax.admin.commoncode.data4"), width: 70, align: "left", editor: "text"}
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, {selectedClear: true});
                }
            }
        });

        axboot.buttonClick(this, "data-grid-view-02-btn", {
            "add": function () {
            	//console.log("add");
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            "delete": function () {
            	//console.log("delete");
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            }
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == "modified" || _type == "deleted") {
            list = ax5.util.filter(_list, function () {
                return this.groupCd && this.code;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({__created__: true, posUseYn: "N", useYn: "Y"}, "last");
    }
});