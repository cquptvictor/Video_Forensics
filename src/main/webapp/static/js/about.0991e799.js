(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["about"],{3286:function(t,e,a){"use strict";var s=a("c1a4"),i=a.n(s);i.a},a2f9:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"news"},[a("div",{staticClass:"newsHead"},[a("ul",{staticClass:"tabs"},[a("li",[t._v(t._s(t.title))])]),a("ul",{staticClass:"add"},[a("li",{on:{click:function(e){return t.publish()}}},[t._v("发布"+t._s(t.title)+" ")])])]),a("div",{style:{display:t.noData?"none":"block"}},[a("div",{staticClass:"showList"},t._l(t.showList,(function(e,s){return a("el-row",{key:s},[a("el-col",{attrs:{span:20},nativeOn:{click:function(a){return t.showDetail(e.id)}}},[a("div",{staticClass:"main"},[a("p",[t._v(t._s(e.title))]),a("p",[t._v("发布人："+t._s(e.author)+" ")]),a("p",[t._v("发布时间："+t._s(e.time))])])]),a("el-col",{attrs:{span:3}}),a("el-col",{attrs:{span:1}},[a("div",{staticClass:"operate"},[a("i",{staticClass:"el-icon-delete",on:{click:function(e){return t.ifSureDo(s)}}})])])],1)})),1),a("div",{staticClass:"paging"},[t.ispage?a("el-pagination",{attrs:{"current-page":t.activePage,"page-size":8,"pager-count":5,layout:"prev, pager, next",total:t.totalPage},on:{"current-change":t.handleCurrentChange,"update:currentPage":function(e){t.activePage=e},"update:current-page":function(e){t.activePage=e}}}):t._e()],1)]),a("div",{style:{display:t.noData?"block":"none"}},[t._m(0)])])},i=[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"notice"},[s("img",{attrs:{src:a("d3e2")}}),s("p",[t._v("暂无内容！")])])}],n=(a("ac4d"),a("8a81"),a("ac6a"),a("7f7f"),{name:"news",data:function(){return{activePage:1,totalPage:1,ispage:!1,getDataUrl:"",title:"",name:"",param:new FormData,showList:[],noData:!1}},mounted:function(){this.getToken()&&this.getName()},methods:{getName:function(){var t=this.$route.params.name;this.name=t;var e=sessionStorage.getItem("nowPage");if(this.activePage=e?parseInt(e):this.activePage,"news"==t)this.title="新闻",this.getDataUrl=this.$baseUrl+"/article/search",this.param.set("isApp",0),this.param.set("type","news");else{this.title="通知",this.getDataUrl=this.$baseUrl+"/notices";var a=sessionStorage.getItem("courseID");this.param.set("courseId",a)}this.param.set("token",this.getToken()),this.param.set("pageNum",8),this.param.set("currentPage",this.activePage),this.getNewsList(this.getDataUrl)},getNewsList:function(t){var e=this;this.axios.post(t,this.param).then((function(t){var a=t.data,s={};e.showList=[];var i=function(t){return s={id:t.id,title:t.title,publisher:t.publisherId,time:t.time,author:t.publisherName},s};if(200==a.code){if(console.log(a),e.totalPage=a.data.totalNumber,Array.isArray(a.data.pageData)){var n=!0,r=!1,o=void 0;try{for(var c,l=a.data.pageData[Symbol.iterator]();!(n=(c=l.next()).done);n=!0){var h=c.value;e.showList.push(i(h))}}catch(u){r=!0,o=u}finally{try{n||null==l.return||l.return()}finally{if(r)throw o}}}else e.$message.warning(e.name+"列表为空！"),e.noData=!0;a.data.totalNumber>8&&(e.ispage=!0)}else e.$message.error(a.message)})).catch((function(t){console.log(t)}))},handleCurrentChange:function(t){console.log("当前页: ".concat(t)),this.param.set("currentPage",this.activePage),this.getNewsList(this.getDataUrl),sessionStorage.setItem("nowPage",JSON.stringify(this.activePage))},showDetail:function(t){console.log(t),this.$router.push({path:"/Show",query:{name:this.name,id:t}})},publish:function(){this.$router.push({path:"/Publish",query:{name:this.name}})},ifSureDo:function(t){var e=this;this.$confirm("确认删除该条信息？").then((function(){e.deleteList(t)})).catch((function(){}))},deleteList:function(t){var e=this,a=this,s=new FormData,i="";switch(this.name){case"news":i=this.$baseUrl+"/article/delete",s.set("id",this.showList[t].id),s.set("publisherId",this.showList[t].publisher);break;case"inform":i=this.$baseUrl+"/dNotice",s.set("id",this.showList[t].id);break;default:break}s.set("token",this.getToken()),console.log(this.showList[t].id),this.axios.post(i,s).then((function(t){200==t.data.code?(e.getNewsList(e.getDataUrl),a.$message.success("删除成功！")):a.$message.error("删除失败！")}))},getToken:function(){var t=sessionStorage.getItem("token");return t.slice(1,t.length-1)}}}),r=n,o=(a("3286"),a("2877")),c=Object(o["a"])(r,s,i,!1,null,"0459d85e",null);e["default"]=c.exports},c1a4:function(t,e,a){}}]);
//# sourceMappingURL=about.0991e799.js.map