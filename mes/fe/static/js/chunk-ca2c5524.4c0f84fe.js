(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ca2c5524"],{"09f4":function(e,t,i){"use strict";i.d(t,"a",(function(){return o})),Math.easeInOutQuad=function(e,t,i,n){return e/=n/2,e<1?i/2*e*e+t:(e--,-i/2*(e*(e-2)-1)+t)};var n=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function r(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function l(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function o(e,t,i){var o=l(),a=e-o,s=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=s;var l=Math.easeInOutQuad(c,o,a,t);r(l),c<t?n(e):i&&"function"===typeof i&&i()};u()}},6724:function(e,t,i){"use strict";i("8d41");var n="@@wavesContext";function r(e,t){function i(i){var n=Object.assign({},t.value),r=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},n),l=r.ele;if(l){l.style.position="relative",l.style.overflow="hidden";var o=l.getBoundingClientRect(),a=l.querySelector(".waves-ripple");switch(a?a.className="waves-ripple":(a=document.createElement("span"),a.className="waves-ripple",a.style.height=a.style.width=Math.max(o.width,o.height)+"px",l.appendChild(a)),r.type){case"center":a.style.top=o.height/2-a.offsetHeight/2+"px",a.style.left=o.width/2-a.offsetWidth/2+"px";break;default:a.style.top=(i.pageY-o.top-a.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",a.style.left=(i.pageX-o.left-a.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return a.style.backgroundColor=r.color,a.className="waves-ripple z-active",!1}}return e[n]?e[n].removeHandle=i:e[n]={removeHandle:i},i}var l={bind:function(e,t){e.addEventListener("click",r(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[n].removeHandle,!1),e.addEventListener("click",r(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[n].removeHandle,!1),e[n]=null,delete e[n]}},o=function(e){e.directive("waves",l)};window.Vue&&(window.waves=l,Vue.use(o)),l.install=o;t["a"]=l},"8d41":function(e,t,i){},f982:function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"app-container"},[i("div",{staticClass:"filter-container"},[i("el-form",{ref:"filterForm",attrs:{model:e.listQuery,inline:!0}},[i("el-form-item",{attrs:{label:"",prop:"name"}},[i("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"角色名",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleFilter(t)}},model:{value:e.listQuery.name,callback:function(t){e.$set(e.listQuery,"name",t)},expression:"listQuery.name"}})],1),e._v(" "),i("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v("搜索\n      ")]),e._v(" "),i("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",on:{click:function(t){e.resetForm("filterForm"),e.handleFilter()}}},[e._v("重置")]),e._v(" "),i("el-button",{staticClass:"filter-item",attrs:{type:"success",icon:"el-icon-edit"},on:{click:e.handleAdd}},[e._v("\n        添加\n      ")])],1)],1),e._v(" "),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],key:e.tableKey,attrs:{data:e.list,border:"",fit:"","highlight-current-row":""}},[i("el-table-column",{attrs:{label:"序号","min-width":"20px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.$index)+"\n      ")]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"角色编码","min-width":"100px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.code))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"角色名","min-width":"100px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.name))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"描述","min-width":"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.description))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"操作",align:"center","min-width":"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{type:"primary",icon:"el-icon-edit",size:"mini"},on:{click:function(i){return e.handleUpdate(t.row)}}},[e._v("编辑\n        ")]),e._v(" "),i("el-button",{attrs:{icon:"el-icon-delete",size:"mini",type:"danger"},on:{click:function(i){return e.handleDelete(t.row,"true")}}},[e._v("删除\n        ")])]}}])})],1),e._v(" "),i("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.current,limit:e.listQuery.size},on:{"update:page":function(t){return e.$set(e.listQuery,"current",t)},"update:limit":function(t){return e.$set(e.listQuery,"size",t)},pagination:e.getList}}),e._v(" "),i("el-dialog",{attrs:{"close-on-click-modal":!1,title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,width:"600px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[i("el-form",{ref:"roleForm",attrs:{rules:e.rules,model:e.temp,"label-position":"right","label-width":"150px"}},[i("el-form-item",{attrs:{label:"角色编码：",prop:"code"}},[i("el-input",{attrs:{placeholder:"角色编码"},model:{value:e.temp.code,callback:function(t){e.$set(e.temp,"code",t)},expression:"temp.code"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"不良代码名称：",prop:"name"}},[i("el-input",{attrs:{placeholder:"角色名"},model:{value:e.temp.name,callback:function(t){e.$set(e.temp,"name",t)},expression:"temp.name"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"描述：",prop:"description"}},[i("el-input",{attrs:{autosize:{minRows:2,maxRows:4},type:"textarea",placeholder:"描述"},model:{value:e.temp.description,callback:function(t){e.$set(e.temp,"description",t)},expression:"temp.description"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"菜单"}},[i("el-tree",{ref:"tree",staticClass:"permission-tree",attrs:{"check-strictly":e.checkStrictly,data:e.treeData,props:e.defaultProps,"show-checkbox":"","node-key":"name"},on:{"check-change":e.handleCheckChange}})],1)],1),e._v(" "),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),i("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(t){"create"===e.dialogStatus?e.submit():e.updateData()}}},[e._v("确认")])],1)],1)],1)},r=[],l=(i("6762"),i("2fdb"),i("75fc")),o=(i("7f7f"),i("ac4d"),i("8a81"),i("ac6a"),i("df7c"),i("ed08")),a=i("cc5e"),s=(i("a18c"),i("6724")),c=i("333d"),u={name:"Role",components:{Pagination:c["a"]},directives:{waves:s["a"]},data:function(){return{tableKey:0,list:[],total:0,listLoading:!0,listQuery:{current:1,size:10,name:void 0},routes:[],temp:{id:void 0,name:"",code:"",description:""},tempCopy:null,dialogFormVisible:!1,dialogStatus:"",textMap:{update:"编辑",create:"添加"},checkStrictly:!1,defaultProps:{children:"children",label:"title"},rules:{name:[{required:!0,trigger:"blur",message:"请填写不良代码名称"}],code:[{required:!0,trigger:"blur",message:"请填写不良代码编码"}]}}},computed:{treeData:function(){return this.serviceRoutes=Object(o["b"])(this.generateTreeFromList(this.routes)),this.generateRoutes(this.serviceRoutes)}},created:function(){this.tempCopy=Object(o["b"])(this.temp),this.listLoading=!0,this.getRoutes(),this.getList()},methods:{getList:function(){var e=this;this.listLoading=!0,Object(a["c"])(this.listQuery).then((function(t){e.list=t.queryResult.list,e.total=t.queryResult.total,e.listLoading=!1}))},handleFilter:function(){this.listQuery.current=1,this.getList()},generateTreeFromList:function(e){var t=JSON.parse(JSON.stringify(e)),i={};for(var n in t)i[t[n].id]=t[n];return t.filter((function(e){var n=t.filter((function(t){return e.id===t.pid}));return n.length>0&&(e.children=n),!i[e.pid]}))},generateRoutes:function(e){var t=[],i=!0,n=!1,r=void 0;try{for(var l,o=e[Symbol.iterator]();!(i=(l=o.next()).done);i=!0){var a=l.value;if(!a.hidden){var s=this.onlyOneShowingChild(a.children,a);a.children&&s&&!a.alwaysShow&&(a=s);var c={name:a.code,title:a.name};a.children&&(c.children=this.generateRoutes(a.children)),t.push(c)}}}catch(u){n=!0,r=u}finally{try{i||null==o.return||o.return()}finally{if(n)throw r}}return t},onlyOneShowingChild:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[],t=arguments.length>1?arguments[1]:void 0,i=null,n=e.filter((function(e){return!e.hidden}));return 1===n.length?(i=n[0],i):0===n.length&&(i=t,i)},getRoutes:function(){var e=this;Object(a["d"])({}).then((function(t){e.routes=t.queryResult.list}))},resetForm:function(e){if(void 0===this.$refs[e])return!1;this.$refs[e].resetFields(),this.temp=Object(o["b"])(this.tempCopy)},handleCheckChange:function(e,t,i){var n=this;this.checkStrictly=!0,this.$nextTick((function(){n.$refs.tree.setCheckedKeys([].concat(Object(l["a"])(n.$refs.tree.getCheckedKeys()),Object(l["a"])(n.$refs.tree.getHalfCheckedKeys()))),n.checkStrictly=!1}))},handleAdd:function(){var e=this;this.resetForm("roleForm"),this.dialogStatus="create",this.dialogFormVisible=!0,this.$refs.tree&&this.$refs.tree.setCheckedKeys([]),this.$nextTick((function(){e.$refs["roleForm"].clearValidate()}))},submit:function(){var e=this;this.$refs["roleForm"].validate((function(t){if(t){var i=Object(o["b"])(e.temp),n=e.$refs.tree.getCheckedKeys(),r=e.routes.filter((function(e){return n.includes(e.code)}));i.menuList=r,Object(a["a"])(i).then((function(t){e.list.unshift(t.model),e.total++,e.dialogFormVisible=!1,e.$notify({title:"成功",message:"创建成功",type:"success",duration:2e3})}))}}))},handleUpdate:function(e){var t=this;this.dialogStatus="update",this.temp=Object(o["b"])(e),this.dialogFormVisible=!0,this.checkStrictly=!0,this.$nextTick((function(){t.$refs.tree.setCheckedKeys(t.temp.menuList.map((function(e){return e.code}))),t.checkStrictly=!1,t.$refs["roleForm"].clearValidate()}))},updateData:function(){var e=this;this.$refs["roleForm"].validate((function(t){if(t){var i=Object(o["b"])(e.temp),n=e.$refs.tree.getCheckedKeys(),r=e.routes.filter((function(e){return n.includes(e.code)}));i.menuList=r,Object(a["e"])(i).then((function(){var t=!0,n=!1,r=void 0;try{for(var l,o=e.list[Symbol.iterator]();!(t=(l=o.next()).done);t=!0){var a=l.value;if(a.id===i.id){var s=e.list.indexOf(a);e.list.splice(s,1,i);break}}}catch(c){n=!0,r=c}finally{try{t||null==o.return||o.return()}finally{if(n)throw r}}e.dialogFormVisible=!1,e.$notify({title:"成功",dangerouslyUseHTMLString:!0,message:"更新成功",type:"success",duration:2e3})}))}}))},handleDelete:function(e){var t=this;this.$confirm("此操作将永久删除该角色, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(a["b"])(e.id).then((function(){t.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3});var i=t.list.indexOf(e);t.list.splice(i,1)}))}))}}},d=u,f=i("2877"),m=Object(f["a"])(d,n,r,!1,null,null,null);t["default"]=m.exports}}]);