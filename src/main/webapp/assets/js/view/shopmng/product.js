var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: "GET",
            url: ["product"],
            data: caller.searchView.getData(),
            callback: function (res) {
            	/*//중복기능추가용: 조회할때 기존 조회건인지를 확인하기 위해 __searched__ 속성을 더 붙여준다.
            	//__created__로 가능
            	for ( var i = 0; i < res.list.length; i++) {
            		res.list[i].__searched__= true;
                }*/
                caller.gridView01.setData(res);
            }
        });
        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        var saveList = [].concat(caller.gridView01.getData("modified"));
        saveList = saveList.concat(caller.gridView01.getData("deleted"));
        //alert(JSON.stringify(caller.gridView01.getData("modified")));
        //alert(JSON.stringify(saveList));
        axboot.ajax({
            type: "PUT",
            url: ["product"],
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push(LANG("onsave"));
            },
        	options: {
	            onError: function(err){
	            	//{"message":"신규로 추가하신 [제품코드]코드는 이미 등록된 제품코드입니다."}
	               axDialog.alert({
	            	    title: "Error",
	                    theme: "primary",
	                    width:450,
	                    msg: err.message
	                });    
	         }
	       }
        });
    },
    PAGE_EXCEL: function (caller, act, data) {
    	var fileName = ax5.util.date((new Date()), {return:'yyyy_MM_dd'})+"_product.xls"
    	//caller.gridView01.target.exportExcel("grid-to-excel.xls");
    	caller.gridView01.target.exportExcel(fileName);
    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow("selected");
    }
});

var CODE = {};  //추가
// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
	var _this = this;  
    axboot
        .call({
            type: "GET", url: "/api/v1/commonCodes", data: {groupCd: "ORIGIN"},
            callback: function (res) {
                var originList = [];
                res.list.forEach(function (n) {
                	originList.push({
                                //여기서 CD, NM의 문자를 사용한 이유는 아래 gridView의 원산지의
                                // optionValue와 optionText를 CD와 NM으로 사용했기 때문이다.
                		CD: n.code, NM: n.name + "(" + n.code + ")"                        
                    });
                });
                this.originList = originList;
                //alert(JSON.stringify(this.originList));
            }
        })
        .done(function () {
            CODE = this; // this는 call을 통해 수집된 데이터들.
            //위쪽 추가
            _this.gridView01.initView();  // this --> _this 로 변경
            _this.searchView.initView();  // this --> _this 로 변경
            _this.pageButtonView.initView();  // this --> _this 로 변경
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });  //추가

    //this.pageButtonView.initView();
    //this.searchView.initView();
    //this.gridView01.initView();

    //ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
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
            },
            "excel": function () {
                ACTIONS.dispatch(ACTIONS.PAGE_EXCEL);
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

var fn_disabled = function () {
    return function () {
        return this.value != "edit";
    };
};

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;
        this.originList = CODE.originList; //추가
        
        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            sortable: true,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
            	/*{key: "prdtCd", label: "제품코드", width: 100, align: "center", editor: {
            		type : "text", disabled: function () {
                        // 조건별로 disabled 처리
                        return this.item.__searched__;
                    }
            	  }
            	},*/
            	{key: "prdtCd", label: "제품코드", width: 100, align: "center", editor: {type: "text", disabled: "notCreated"}},
                {key: "prdtNm", label: "제품명", width: 200, align: "center", editor: "textBtn"},
                {key: "origin", label: "원산지", width: 100,align: "center", editor: {
                    type: "select", config: {
                        columnKeys: {
                            optionValue: "CD", optionText: "NM"
                        },
                        options: this.originList //추가
                        /*
                            // this.originList 에 아래와 같이 셋팅되어있다. 만약 연동하지 않고 하드코딩으로 
                            // 처리하고 싶으면 this.originList 자리에 아래와 같은 형태로만 입력시켜주면 된다.
                        	[
                        	 {"CD":"KR","NM":"한국(KR)"},
                        	 {"CD":"US","NM":"미국(US)"},
                        	 {"CD":"JP","NM":"일본(JP)"}
                        	]
                        */                        
                    }
                  }
                },
                {key: "purchasePrice", label: "매입가격", width: 150, align: "right", editor: "number"},
                {key: "salesPrice", label: "판매가격", width: 150, align: "right", editor: "number"} 
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, {selectedClear: true});
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
        //alert(_type);
        if (_type == "modified" || _type == "deleted") {
            list = ax5.util.filter(_list, function () {
                return this.prdtCd;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        //this.target.addRow({__created__: true, posUseYn: "N", useYn: "Y"}, "last");
    	this.target.addRow({__created__: true, origin: "KR" }, "last");
    },
    delRow: function delRow(_type) {    
    	this.target.deleteRow(_type);  //여기서 무조건 deleteList에 추가해주고 있는것 같다.
    	//그렇다보니 기존 조회된 데이타가 아닌 신규로 추가한 후 삭제해도 추가되기 때문에 
    	//저장시 안넘어가도 되는 데이타(화면상에서만 행추가후 삭제시)까지 날라간다. 
        //중복기능추가시: 조회된것이 아닌경우 deleteList에서 빼준다.        
        for(i=0;i<this.target.deletedList.length ;i++)
        {
        	if(this.target.deletedList[i].__created__)
        	{
        		this.target.deletedList[i]=null;
        	}
        }
    }
});