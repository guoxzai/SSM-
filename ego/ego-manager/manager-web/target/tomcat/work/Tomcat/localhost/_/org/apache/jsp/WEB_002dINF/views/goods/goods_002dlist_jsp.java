/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-03-27 12:18:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.goods;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class goods_002dlist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/views/goods/../taglib.jsp", Long.valueOf(1553348816983L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<table class=\"easyui-datagrid\" id=\"itemList\" title=\"商品列表\" \r\n");
      out.write("       data-options=\"singleSelect:false,collapsible:true,pagination:true,url:'/goods/getData',method:'get',pageSize:30,toolbar:toolbar\">\r\n");
      out.write("    <thead>\r\n");
      out.write("        <tr>\r\n");
      out.write("        \t<th data-options=\"field:'ck',checkbox:true\"></th>\r\n");
      out.write("        \t<th data-options=\"field:'id',width:60\">商品ID</th>\r\n");
      out.write("            <th data-options=\"field:'goodsName',width:200\">商品标题</th>\r\n");
      out.write("            <th data-options=\"field:'catId',width:100\">类目</th>\r\n");
      out.write("            <th data-options=\"field:'keywords',width:100\">卖点</th>\r\n");
      out.write("            <th data-options=\"field:'shopPrice',width:70,align:'right',formatter:ego.formatPrice\">本店价格</th>\r\n");
      out.write("            <th data-options=\"field:'storeCount',width:70,align:'right'\">库存数量</th>\r\n");
      out.write("            <th data-options=\"field:'goodsSn',width:100\">商品编号</th>\r\n");
      out.write("            <th data-options=\"field:'isOnSale',width:60,align:'center',formatter:ego.formatItemStatus\">状态</th>\r\n");
      out.write("            <th data-options=\"field:'onTime',width:130,align:'center',formatter:ego.formatDateTime\">商品上架时间</th>\r\n");
      out.write("            <th data-options=\"field:'lastUpdate',width:130,align:'center',formatter:ego.formatDateTime\">更新日期</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </thead>\r\n");
      out.write("</table>\r\n");
      out.write("<div id=\"itemEditWindow\" class=\"easyui-window\" title=\"编辑商品\" data-options=\"modal:true,closed:true,iconCls:'icon-save',href:'/goods/edit'\" style=\"width:80%;height:80%;padding:10px;\">\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("    function getSelectionsIds(){\r\n");
      out.write("    \tvar itemList = $(\"#itemList\");\r\n");
      out.write("    \tvar sels = itemList.datagrid(\"getSelections\");\r\n");
      out.write("    \tvar ids = [];\r\n");
      out.write("    \tfor(var i in sels){\r\n");
      out.write("    \t\tids.push(sels[i].id);\r\n");
      out.write("    \t}\r\n");
      out.write("    \tids = ids.join(\",\");\r\n");
      out.write("    \treturn ids;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    var toolbar = [{\r\n");
      out.write("        text:'新增',\r\n");
      out.write("        iconCls:'icon-add',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \t$(\".tree-title:contains('新增商品')\").parent().click();\r\n");
      out.write("        }\r\n");
      out.write("    },{\r\n");
      out.write("        text:'编辑',\r\n");
      out.write("        iconCls:'icon-edit',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \tvar ids = getSelectionsIds();\r\n");
      out.write("        \tif(ids.length == 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','必须选择一个商品才能编辑!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \tif(ids.indexOf(',') > 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','只能选择一个商品!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \t\r\n");
      out.write("        \t$(\"#itemEditWindow\").window({\r\n");
      out.write("        \t\tonLoad :function(){\r\n");
      out.write("        \t\t\t//获取选中之后的数据\r\n");
      out.write("        \t\t\tvar data = $(\"#itemList\").datagrid(\"getSelections\")[0];\r\n");
      out.write("        \t\t\t//通过easyui form方法，加载数据\r\n");
      out.write("        \t\t\t$(\"#itemeEditForm\").form(\"load\",data);\r\n");
      out.write("        \t\t\t\r\n");
      out.write("        \t\t\t// 加载商品描述\r\n");
      out.write("        \t\t\t$.getJSON('/goods/query/desc/'+data.id,function(_data){\r\n");
      out.write("        \t\t\t\tif(_data.status == 200){\r\n");
      out.write("        \t\t\t\t\t//UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);\r\n");
      out.write("        \t\t\t\t\titemEditEditor.html(_data.msg);\r\n");
      out.write("        \t\t\t\t}\r\n");
      out.write("        \t\t\t});\r\n");
      out.write("        \t\t\t\r\n");
      out.write("        \t\t\t//加载商品规格\r\n");
      out.write("        \t\t\t$.getJSON('/goods/attrparam/'+data.id,function(_data){\r\n");
      out.write("        \t\t\t\tif(_data && _data.status == 200 && _data.data && _data.data.paramData){\r\n");
      out.write("        \t\t\t\t\t$(\"#itemeEditForm .params\").show();\r\n");
      out.write("        \t\t\t\t\t$(\"#itemeEditForm [name=itemParams]\").val(_data.data.paramData);\r\n");
      out.write("        \t\t\t\t\t$(\"#itemeEditForm [name=itemParamId]\").val(_data.data.id);\r\n");
      out.write("        \t\t\t\t\t\r\n");
      out.write("        \t\t\t\t\t//回显商品规格\r\n");
      out.write("        \t\t\t\t\t/*  var paramData = JSON.parse(_data.data.paramData); */\r\n");
      out.write("        \t\t\t\t\t var paramData = _data.data.paramData;\r\n");
      out.write("        \t\t\t\t\t\r\n");
      out.write("        \t\t\t\t\t var html = \"<ul>\";\r\n");
      out.write("        \t\t\t\t\t for(var i in paramData){\r\n");
      out.write("        \t\t\t\t\t\t var pd = paramData[i];\r\n");
      out.write("        \t\t\t\t\t\t html+=\"<li><input type=\\\"hidden\\\" value='\"+pd.catId+\"'/>\";\r\n");
      out.write("        \t\t\t\t\t\t \r\n");
      out.write("        \t\t\t\t\t\t html+=\"<table>\";\r\n");
      out.write("        \t\t\t\t\t\t html+=\"<tr><td colspan=\\\"2\\\" class=\\\"group\\\">\"+pd.group+\"</td></tr>\";\r\n");
      out.write("        \t\t\t\t\t\t \r\n");
      out.write("        \t\t\t\t\t\t for(var j in pd.params){\r\n");
      out.write("        \t\t\t\t\t\t\t var ps = pd.params[j];\r\n");
      out.write("        \t\t\t\t\t\t\t html+=\"<tr><td class=\\\"param\\\"><span>\"+ps.k+\"</span>: </td><td><input autocomplete=\\\"off\\\" type=\\\"text\\\" value='\"+ps.v+\"'/></td></tr>\";\r\n");
      out.write("        \t\t\t\t\t\t }\r\n");
      out.write("        \t\t\t\t\t\t \r\n");
      out.write("        \t\t\t\t\t\t html+=\"</li></table>\";\r\n");
      out.write("        \t\t\t\t\t }\r\n");
      out.write("        \t\t\t\t\t html+= \"</ul>\";\r\n");
      out.write("        \t\t\t\t\t $(\"#itemeEditForm .params td\").eq(1).html(html);\r\n");
      out.write("        \t\t\t\t}\r\n");
      out.write("        \t\t\t});\r\n");
      out.write("        \t\t\t\r\n");
      out.write("        \t\t\tego.init({\r\n");
      out.write("        \t\t\t\t\"pics\" : data.image,\r\n");
      out.write("        \t\t\t\t\"cid\" : data.cid,\r\n");
      out.write("        \t\t\t\tfun:function(node){\r\n");
      out.write("        \t\t\t\t\tego.changeItemParam(node, \"itemeEditForm\");\r\n");
      out.write("        \t\t\t\t}\r\n");
      out.write("        \t\t\t});\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t}).window(\"open\");\r\n");
      out.write("        }\r\n");
      out.write("    },{\r\n");
      out.write("        text:'删除',\r\n");
      out.write("        iconCls:'icon-cancel',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \tvar ids = getSelectionsIds();\r\n");
      out.write("        \tif(ids.length == 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','未选中商品!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \t$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品吗？',function(r){\r\n");
      out.write("        \t    if (r){\r\n");
      out.write("        \t    \tvar params = {\"ids\":ids};\r\n");
      out.write("                \t$.post(\"/rest/goods/delete\",params, function(data){\r\n");
      out.write("            \t\t\tif(data.status == 200){\r\n");
      out.write("            \t\t\t\t$.messager.alert('提示','删除商品成功!',undefined,function(){\r\n");
      out.write("            \t\t\t\t\t$(\"#itemList\").datagrid(\"reload\");\r\n");
      out.write("            \t\t\t\t});\r\n");
      out.write("            \t\t\t}\r\n");
      out.write("            \t\t});\r\n");
      out.write("        \t    }\r\n");
      out.write("        \t});\r\n");
      out.write("        }\r\n");
      out.write("    },'-',{\r\n");
      out.write("        text:'下架',\r\n");
      out.write("        iconCls:'icon-remove',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \tvar ids = getSelectionsIds();\r\n");
      out.write("        \tif(ids.length == 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','未选中商品!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \t$.messager.confirm('确认','确定下架ID为 '+ids+' 的商品吗？',function(r){\r\n");
      out.write("        \t    if (r){\r\n");
      out.write("        \t    \tvar params = {\"ids\":ids};\r\n");
      out.write("                \t$.post(\"/rest/goods/instock\",params, function(data){\r\n");
      out.write("            \t\t\tif(data.status == 200){\r\n");
      out.write("            \t\t\t\t$.messager.alert('提示','下架商品成功!',undefined,function(){\r\n");
      out.write("            \t\t\t\t\t$(\"#itemList\").datagrid(\"reload\");\r\n");
      out.write("            \t\t\t\t});\r\n");
      out.write("            \t\t\t}\r\n");
      out.write("            \t\t});\r\n");
      out.write("        \t    }\r\n");
      out.write("        \t});\r\n");
      out.write("        }\r\n");
      out.write("    },{\r\n");
      out.write("        text:'上架',\r\n");
      out.write("        iconCls:'icon-remove',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \tvar ids = getSelectionsIds();\r\n");
      out.write("        \tif(ids.length == 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','未选中商品!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \t$.messager.confirm('确认','确定上架ID为 '+ids+' 的商品吗？',function(r){\r\n");
      out.write("        \t    if (r){\r\n");
      out.write("        \t    \tvar params = {\"ids\":ids};\r\n");
      out.write("                \t$.post(\"/rest/goods/reshelf\",params, function(data){\r\n");
      out.write("            \t\t\tif(data.status == 200){\r\n");
      out.write("            \t\t\t\t$.messager.alert('提示','上架商品成功!',undefined,function(){\r\n");
      out.write("            \t\t\t\t\t$(\"#itemList\").datagrid(\"reload\");\r\n");
      out.write("            \t\t\t\t});\r\n");
      out.write("            \t\t\t}\r\n");
      out.write("            \t\t});\r\n");
      out.write("        \t    }\r\n");
      out.write("        \t});\r\n");
      out.write("        }\r\n");
      out.write("    }];\r\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/views/goods/../taglib.jsp(4,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    // /WEB-INF/views/goods/../taglib.jsp(4,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/goods/../taglib.jsp(4,0) '${pageContext.request.contextPath }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${pageContext.request.contextPath }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }
}
