/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-03-26 14:47:30 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.goods;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class goods_002dattribute_002dadd_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<form id=\"itemParamAddForm\">\r\n");
      out.write("<table cellpadding=\"5\" style=\"margin-left: 30px\" id=\"itemParamAddTable\" class=\"itemParam\">\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td>商品类目:</td>\r\n");
      out.write("\t\t<td><a href=\"javascript:void(0)\" class=\"easyui-linkbutton selectItemCat\">选择类目</a> \r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"catId\" style=\"width: 280px;\"></input>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td>属性名称:</td>\r\n");
      out.write("\t\t<td>\r\n");
      out.write("\t\t\t<input type=\"text\" name=\"attrName\" style=\"width: 280px;\"></input>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td>能否进行检索:</td>\r\n");
      out.write("\t\t<td>\r\n");
      out.write("\t\t\t<input type=\"radio\" name=\"attrIndex\" value=\"0\" />不需要检索\r\n");
      out.write("\t\t\t<input type=\"radio\" name=\"attrIndex\" value=\"1\" />关键字检索索\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td>该属性值的录入方式:</td>\r\n");
      out.write("\t\t<td>\r\n");
      out.write("\t\t\t<input type=\"radio\" name=\"attrInputType\" value=\"0\" />手工录入\r\n");
      out.write("\t\t\t<input type=\"radio\" name=\"attrInputType\" value=\"1\" />从下面的列表中选择（一行代表一个可选值）\r\n");
      out.write("\t\t\t<input type=\"radio\" name=\"attrInputType\" value=\"2\" />多行文本框\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td>可选值列表:</td>\r\n");
      out.write("\t\t<td>\r\n");
      out.write("\t\t\t<textarea name=\"attrValues\" rows=\"\" cols=\"\"></textarea>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td></td>\r\n");
      out.write("\t\t<td>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton submit\">提交</a>\r\n");
      out.write("\t    \t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton close\">关闭</a>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      out.write("<div  class=\"itemParamAddTemplate\" style=\"display: none;\">\r\n");
      out.write("\t<li class=\"param\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<input class=\"easyui-textbox\" style=\"width: 150px;\" name=\"group\"/>&nbsp;<a href=\"javascript:void(0)\" class=\"easyui-linkbutton addParam\"  title=\"添加参数\" data-options=\"plain:true,iconCls:'icon-add'\"></a>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<span>|-------</span><input  style=\"width: 150px;\" class=\"easyui-textbox\" name=\"param\"/>&nbsp;<a href=\"javascript:void(0)\" class=\"easyui-linkbutton delParam\" title=\"删除\" data-options=\"plain:true,iconCls:'icon-cancel'\"></a>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</li>\r\n");
      out.write("</div>\r\n");
      out.write("<script style=\"text/javascript\">\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\tego.initItemCat({\r\n");
      out.write("\t\t\tfun:function(node){\r\n");
      out.write("\t\t\t$(\".addGroupTr\").hide().find(\".param\").remove();\r\n");
      out.write("\t\t\t\t//  判断选择的类目是否已经添加过规格\r\n");
      out.write("\t\t\t  $.getJSON(\"/goods/param/query/itemcatid/\" + node.id,function(data){\r\n");
      out.write("\t\t\t\t  if(data.status == 200 && data.data){\r\n");
      out.write("\t\t\t\t\t  $.messager.alert(\"提示\", \"该类目已经添加，请选择其他类目。\", undefined, function(){\r\n");
      out.write("\t\t\t\t\t\t $(\"#itemParamAddTable .selectItemCat\").click();\r\n");
      out.write("\t\t\t\t\t  });\r\n");
      out.write("\t\t\t\t\t  return ;\r\n");
      out.write("\t\t\t\t  }\r\n");
      out.write("\t\t\t\t  $(\".addGroupTr\").show();\r\n");
      out.write("\t\t\t  });\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\".addGroup\").click(function(){\r\n");
      out.write("\t\t\t  var temple = $(\".itemParamAddTemplate li\").eq(0).clone();\r\n");
      out.write("\t\t\t  $(this).parent().parent().append(temple);\r\n");
      out.write("\t\t\t  temple.find(\".addParam\").click(function(){\r\n");
      out.write("\t\t\t\t  var li = $(\".itemParamAddTemplate li\").eq(2).clone();\r\n");
      out.write("\t\t\t\t  li.find(\".delParam\").click(function(){\r\n");
      out.write("\t\t\t\t\t  $(this).parent().remove();\r\n");
      out.write("\t\t\t\t  });\r\n");
      out.write("\t\t\t\t  li.appendTo($(this).parentsUntil(\"ul\").parent());\r\n");
      out.write("\t\t\t  });\r\n");
      out.write("\t\t\t  temple.find(\".delParam\").click(function(){\r\n");
      out.write("\t\t\t\t  $(this).parent().remove();\r\n");
      out.write("\t\t\t  });\r\n");
      out.write("\t\t });\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#itemParamAddTable .close\").click(function(){\r\n");
      out.write("\t\t\t$(\".panel-tool-close\").click();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#itemParamAddTable .submit\").click(function(){\r\n");
      out.write("\t\t\tvar params = [];\r\n");
      out.write("\t\t\tvar groups = $(\"#itemParamAddTable [name=group]\");\r\n");
      out.write("\t\t\tgroups.each(function(i,e){\r\n");
      out.write("\t\t\t\tvar p = $(e).parentsUntil(\"ul\").parent().find(\"[name=param]\");\r\n");
      out.write("\t\t\t\tvar _ps = [];\r\n");
      out.write("\t\t\t\tp.each(function(_i,_e){\r\n");
      out.write("\t\t\t\t\tvar _val = $(_e).siblings(\"input\").val();\r\n");
      out.write("\t\t\t\t\tif($.trim(_val).length>0){\r\n");
      out.write("\t\t\t\t\t\t_ps.push(_val);\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\tvar _val = $(e).siblings(\"input\").val();\r\n");
      out.write("\t\t\t\tif($.trim(_val).length>0 && _ps.length > 0){\r\n");
      out.write("\t\t\t\t\tparams.push({\r\n");
      out.write("\t\t\t\t\t\t\"group\":_val,\r\n");
      out.write("\t\t\t\t\t\t\"params\":_ps\r\n");
      out.write("\t\t\t\t\t});\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\tvar url = \"/goods/param/save/\"+$(\"#itemParamAddTable [name=catId]\").val();\r\n");
      out.write("\t\t\talert($(\"#itemParamAddForm\").serialize());\r\n");
      out.write("\t\t\t$.post(url,$(\"#itemParamAddForm\").serialize(),function(data){\r\n");
      out.write("\t\t\t\tif(data.status == 200){\r\n");
      out.write("\t\t\t\t\t$.messager.alert('提示','新增商品规格成功!',undefined,function(){\r\n");
      out.write("\t\t\t\t\t\t$(\".panel-tool-close\").click();\r\n");
      out.write("    \t\t\t\t\t$(\"#itemParamList\").datagrid(\"reload\");\r\n");
      out.write("    \t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
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
