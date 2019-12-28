(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ba865dc6"],{"09b4":function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"app-container"},[i("div",{directives:[{name:"show",rawName:"v-show",value:!e.dialogFormVisible,expression:"!dialogFormVisible"}]},[i("div",{staticClass:"filter-container"},[i("el-form",{ref:"filterForm",attrs:{model:e.listQuery,inline:!0}},[i("el-form-item",{attrs:{label:"",prop:"name"}},[i("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"请输入物料名称",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleFilter(t)}},model:{value:e.listQuery.name,callback:function(t){e.$set(e.listQuery,"name",t)},expression:"listQuery.name"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"",prop:"typeId"}},[i("el-select",{attrs:{filterable:"",placeholder:"物料类型"},on:{change:e.handleFilter},model:{value:e.listQuery.typeId,callback:function(t){e.$set(e.listQuery,"typeId",t)},expression:"listQuery.typeId"}},e._l(e.materialTypes,(function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1),e._v(" "),i("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v("搜索\n        ")]),e._v(" "),i("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",on:{click:function(t){e.resetForm("filterForm"),e.handleFilter()}}},[e._v("重置")]),e._v(" "),i("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"success",icon:"el-icon-edit"},on:{click:e.handleAdd}},[e._v("\n          添加\n        ")])],1)],1),e._v(" "),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],key:e.tableKey,attrs:{data:e.list,border:"",fit:"","highlight-current-row":""}},[i("el-table-column",{attrs:{label:"序号","min-width":"40px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s(t.$index)+"\n        ")]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"料号","min-width":"80px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.code))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"物料名称","min-width":"80px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.name))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"物料类型","min-width":"80px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.typeName))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"描述","min-width":"100px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.description))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"操作",align:"center","min-width":"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{type:"primary",icon:"el-icon-edit",size:"mini"},on:{click:function(i){return e.handleUpdate(t.row)}}},[e._v("编辑\n          ")]),e._v(" "),i("el-button",{attrs:{icon:"el-icon-delete",size:"mini",type:"danger"},on:{click:function(i){return e.handleDelete(t.row,"true")}}},[e._v("删除\n          ")])]}}])})],1),e._v(" "),i("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.current,limit:e.listQuery.size},on:{"update:page":function(t){return e.$set(e.listQuery,"current",t)},"update:limit":function(t){return e.$set(e.listQuery,"size",t)},pagination:e.getList}})],1),e._v(" "),i("div",{directives:[{name:"show",rawName:"v-show",value:e.dialogFormVisible,expression:"dialogFormVisible"}]},[i("el-form",{ref:"materialForm",attrs:{rules:e.rules,model:e.temp,"label-position":"right","label-width":"150px"}},[i("el-form-item",{attrs:{label:"料号：",prop:"code"}},[i("el-input",{model:{value:e.temp.code,callback:function(t){e.$set(e.temp,"code",t)},expression:"temp.code"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"物料名称：",prop:"name"}},[i("el-input",{model:{value:e.temp.name,callback:function(t){e.$set(e.temp,"name",t)},expression:"temp.name"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"物料类型：",prop:"typeId"}},[i("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请选择"},model:{value:e.temp.typeId,callback:function(t){e.$set(e.temp,"typeId",t)},expression:"temp.typeId"}},e._l(e.materialTypes,(function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1),e._v(" "),i("el-form-item",{attrs:{label:"描述：",prop:"description"}},[i("el-input",{attrs:{type:"textarea",rows:2},model:{value:e.temp.description,callback:function(t){e.$set(e.temp,"description",t)},expression:"temp.description"}})],1)],1),e._v(" "),i("div",{staticClass:"form-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),i("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(t){"create"===e.dialogStatus?e.submit():e.updateData()}}},[e._v("确认")])],1)],1)])},a=[],r=(i("ac4d"),i("8a81"),i("ac6a"),i("7f7f"),i("96cf"),i("3b8d")),l=i("ed08"),o=i("49de"),s=i("6724"),c=i("333d"),u={name:"Material",components:{Pagination:c["a"]},directives:{waves:s["a"]},data:function(){return{tableKey:0,list:[],total:0,listLoading:!0,listQuery:{current:1,size:10,name:void 0,typeId:void 0},temp:{id:void 0,name:"",code:"",typeId:"",description:""},tempCopy:null,materialTypes:[],materialTypeMap:null,dialogFormVisible:!1,dialogStatus:"",textMap:{update:"编辑",create:"添加"},rules:{name:[{required:!0,trigger:"blur",message:"请填写工艺名称"}],code:[{required:!0,trigger:"blur",message:"请填写工艺编码"}]}}},created:function(){var e=this;this.tempCopy=Object(l["b"])(this.temp),this.listLoading=!0,this.$nextTick(Object(r["a"])(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,e.getMaterialTypes();case 2:e.getList();case 3:case"end":return t.stop()}}),t)}))))},methods:{handleModifyState:function(e,t){var i=this;Object(o["g"])(t).then((function(e){i.$message({message:"操作成功",type:"success"})}))},getList:function(){var e=this;this.listLoading=!0,Object(o["f"])(this.listQuery).then((function(t){e.list=t.queryResult.list.map((function(t){var i=e.materialTypeMap[t.typeId];return t.typeName=i.name,t})),e.total=t.queryResult.total,e.listLoading=!1}))},getMaterialTypes:function(){var e=Object(r["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,Object(o["e"])({});case 2:t=e.sent,this.materialTypes=t.queryResult.list,this.materialTypeMap=_.fromPairs(this.materialTypes.map((function(e){return[e.id,e]})));case 5:case"end":return e.stop()}}),e,this)})));function t(){return e.apply(this,arguments)}return t}(),handleFilter:function(){this.listQuery.current=1,this.getList()},resetForm:function(e){if(void 0===this.$refs[e])return!1;this.$refs[e].resetFields(),this.temp=Object(l["b"])(this.tempCopy)},handleAdd:function(){var e=this;this.resetForm("materialForm"),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick((function(){e.$refs["materialForm"].clearValidate()}))},submit:function(){var e=this;this.$refs["materialForm"].validate((function(t){if(t){var i=Object(l["b"])(e.temp);Object(o["a"])(i).then((function(t){var n=e.materialTypeMap[i.typeId];t.model.typeName=n.name,e.list.unshift(t.model),e.total++,e.dialogFormVisible=!1,e.$notify({title:"成功",message:"创建成功",type:"success",duration:2e3})}))}}))},handleUpdate:function(e){var t=this;this.dialogStatus="update",this.temp=Object(l["b"])(e),this.dialogFormVisible=!0,this.$nextTick((function(){t.$refs["materialForm"].clearValidate()}))},updateData:function(){var e=this;this.$refs["materialForm"].validate((function(t){if(t){var i=Object(l["b"])(e.temp);Object(o["g"])(i).then((function(){var t=e.materialTypeMap[i.typeId];i.typeName=t.name;var n=!0,a=!1,r=void 0;try{for(var l,o=e.list[Symbol.iterator]();!(n=(l=o.next()).done);n=!0){var s=l.value;if(s.id===i.id){var c=e.list.indexOf(s);e.list.splice(c,1,i);break}}}catch(u){a=!0,r=u}finally{try{n||null==o.return||o.return()}finally{if(a)throw r}}e.dialogFormVisible=!1,e.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})}))}}))},handleDelete:function(e){var t=this;this.$confirm("此操作将永久删除该工艺, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(o["c"])(e.id).then((function(){t.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3});var i=t.list.indexOf(e);t.list.splice(i,1)}))}))}}},d=u,m=(i("fbfc"),i("2877")),p=Object(m["a"])(d,n,a,!1,null,"eb9991d8",null);t["default"]=p.exports},"09f4":function(e,t,i){"use strict";i.d(t,"a",(function(){return l})),Math.easeInOutQuad=function(e,t,i,n){return e/=n/2,e<1?i/2*e*e+t:(e--,-i/2*(e*(e-2)-1)+t)};var n=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function a(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function r(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function l(e,t,i){var l=r(),o=e-l,s=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=s;var r=Math.easeInOutQuad(c,l,o,t);a(r),c<t?n(e):i&&"function"===typeof i&&i()};u()}},"49de":function(e,t,i){"use strict";i.d(t,"e",(function(){return a})),i.d(t,"b",(function(){return r})),i.d(t,"h",(function(){return l})),i.d(t,"d",(function(){return o})),i.d(t,"f",(function(){return s})),i.d(t,"a",(function(){return c})),i.d(t,"g",(function(){return u})),i.d(t,"c",(function(){return d}));var n=i("b775");function a(e){return Object(n["a"])({url:"/basic/materialtype/list",method:"post",data:e})}function r(e){return Object(n["a"])({url:"/basic/materialtype",method:"post",data:e})}function l(e){return Object(n["a"])({url:"/basic/materialtype",method:"put",data:e})}function o(e){return Object(n["a"])({url:"/basic/materialtype/".concat(e),method:"delete"})}function s(e){return Object(n["a"])({url:"/basic/material/list",method:"post",data:e})}function c(e){return Object(n["a"])({url:"/basic/material",method:"post",data:e})}function u(e){return Object(n["a"])({url:"/basic/material",method:"put",data:e})}function d(e){return Object(n["a"])({url:"/basic/material/".concat(e),method:"delete"})}},6724:function(e,t,i){"use strict";i("8d41");var n="@@wavesContext";function a(e,t){function i(i){var n=Object.assign({},t.value),a=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},n),r=a.ele;if(r){r.style.position="relative",r.style.overflow="hidden";var l=r.getBoundingClientRect(),o=r.querySelector(".waves-ripple");switch(o?o.className="waves-ripple":(o=document.createElement("span"),o.className="waves-ripple",o.style.height=o.style.width=Math.max(l.width,l.height)+"px",r.appendChild(o)),a.type){case"center":o.style.top=l.height/2-o.offsetHeight/2+"px",o.style.left=l.width/2-o.offsetWidth/2+"px";break;default:o.style.top=(i.pageY-l.top-o.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",o.style.left=(i.pageX-l.left-o.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return o.style.backgroundColor=a.color,o.className="waves-ripple z-active",!1}}return e[n]?e[n].removeHandle=i:e[n]={removeHandle:i},i}var r={bind:function(e,t){e.addEventListener("click",a(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[n].removeHandle,!1),e.addEventListener("click",a(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[n].removeHandle,!1),e[n]=null,delete e[n]}},l=function(e){e.directive("waves",r)};window.Vue&&(window.waves=r,Vue.use(l)),r.install=l;t["a"]=r},"8d41":function(e,t,i){},9225:function(e,t,i){},fbfc:function(e,t,i){"use strict";var n=i("9225"),a=i.n(n);a.a}}]);