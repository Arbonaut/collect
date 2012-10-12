package jsp.WEB_002dINF.view.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n  <head>\r\n  \t<meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  \t<script type=\"text/javascript\" src=\"script/jquery-1.6.2.min.js\"></script>\r\n    <script type=\"text/javascript\" src=\"script/sessionping.js\"></script>\r\n    <style type=\"text/css\" media=\"screen\"> \r\n\t\thtml, body\t{ height:100%; }\r\n\t\tbody {\r\n\t\t\tfont-family: verdana, serif, monospace;\r\n\t\t\tfont-size: 1.0em;\r\n\t\t\tmargin:0 5 5 5;\t\t\t\r\n\t\t\tpadding:0 10 10 10;\r\n\t\t\toverflow: hidden;\r\n\t\t\ttext-align:center;\r\n\t\t    background-color: #ffffff;\r\n\t\t}\r\n\t\tfieldset {\r\n\t\t\twidth: 80%;\r\n\t\t\tpadding-top: 10px;\r\n\t\t}\r\n\t\tlegend {\r\n\t\t\tfont-weight: bold;\r\n\t\t\tfont-size: 0.8em;\r\n\t\t}\r\n\t\t.login td {\r\n\t\t\tfont-size: small;\r\n\t\t\tfont-family: verdana, serif, monospace;\r\n\t\t\tline-height: 1em;\r\n\t\t\tfont-size: 0.7em;\r\n\t\t\tpadding-top: 10;\r\n\t\t}\r\n\t\tinput.button {\r\n\t\t\tfont-family: verdana, serif, monospace;\r\n\t\t\tline-height: 1em;\r\n\t\t\tfont-size: 1em;\r\n\t\t\tpadding: 3 8 5 8;\r\n\t\t\tbackground-color: #DDDDDD;\r\n    \t\tborder: 1px outset #DDDDDD;\r\n    \t\tcolor: #000000;\r\n    \t\tborder-style: outset;\r\n\t\t}\r\n\r\n");
      out.write("\t\t.login input {\r\n\t\t\theight: 20;\r\n\t\t\tborder: 1px solid #DDDDDD;\r\n\t\t\tfont-family: verdana, serif, monospace;\r\n\t\t\tline-height: 1em;\r\n\t\t\tfont-size: 1em;\r\n\t\t}\r\n\t\t\r\n\t\tdiv.error {\r\n\t\t\tfont-weight: bold;\r\n\t\t\tfont-size: 0.8em;\r\n\t\t\tcolor: red;\r\n\t\t}\r\n\t\t\r\n\t\tdiv.warn {\r\n\t\t\tfont-weight: bold;\r\n\t\t\tfont-size: 0.8em;\r\n\t\t\tcolor: blue;\r\n\t\t}\r\n\t</style>\r\n    <title>OpenForis Collect</title>\r\n  </head>\r\n\r\n  <body onload=\"document.f.j_username.focus();\">\r\n    <table width=\"100%\"  height=\"100%\">\r\n    \t<tr height=\"102\">\r\n    \t\t<td colspan=\"3\" align=\"center\">\r\n    \t\t\t<img alt=\"Open Foris Collect\" src=\"images/banner.jpg\">\r\n    \t\t</td>\r\n    \t</tr>\r\n    \t<tr height=\"50\">\r\n    \t\t<td colspan=\"3\" align=\"center\">\r\n<!-- \t\t\t    <h3>OpenForis Collect</h3> -->\r\n\t\t\t    ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("</td>\r\n    \t</tr>\r\n    \t<tr height=\"30%\">\r\n    \t\t<td width=\"33%\">&nbsp;</td>\r\n    \t\t<td width=\"33%\" style=\"vertical-align: middle;\">\r\n    \t\t<div style=\"margin-left:auto; margin-right:auto; width: 100%\">\r\n\t\t\t\t\t      <table class=\"login\"  width=\"100%\" align=\"center\" style=\"vertical-align: top; height: 100\">\r\n    \t\t\t<form name=\"f\" action=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\" method=\"POST\">\r\n\t\t\t\t\t      <tr>\r\n\t\t\t\t\t      \t<td colspan=\"2\" align=\"center\"><strong style=\"font-size: 13px; \">Please Log In</strong></td>\r\n\t\t\t\t\t      </tr>\r\n\t\t\t\t\t        <tr>\r\n\t\t\t\t\t        \t<td width=\"50%\" align=\"right\">User:</td>\r\n\t\t\t\t\t        \t<td width=\"50%\" align=\"left\"><input type='text' name='j_username' value='");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("'/></td>\r\n\t\t\t\t\t        </tr>\r\n\t\t\t\t\t        <tr>\r\n\t\t\t\t\t        \t<td width=\"50%\" align=\"right\">Password:</td>\r\n\t\t\t\t\t        \t<td width=\"50%\" align=\"left\"><input type='password' name='j_password'></td>\r\n\t\t\t\t\t        </tr>\r\n\t\t\t\t\t        <!-- tr><td><input type=\"checkbox\" name=\"_spring_security_remember_me\"></td><td>Don't ask for my password for two weeks</td></tr-->\r\n\t\t\t\t\t\r\n\t\t\t\t\t        <tr>\r\n\t\t\t\t\t        \t<td colspan='2' width=\"100%\" style=\"text-align: center;\">\r\n\t\t\t\t\t        \t\t<input name=\"submit\" type=\"submit\" class=\"button\" value=\"Submit\" />\r\n\t\t\t\t\t        \t\t<input name=\"reset\" type=\"reset\" class=\"button\" value=\"Reset\"/>\r\n\t\t\t\t        \t\t</td>\r\n\t\t\t        \t\t</tr>\r\n\t\t\t    </form>\r\n\t\t\t\t\t      </table>\r\n\t\t\t    </div>\r\n    \t\t</td>\r\n    \t\t<td width=\"33%\">&nbsp;</td>\r\n    \t</tr>\r\n    \t<tr>\r\n    \t\t<td colspan=\"3\" align=\"center\">\r\n    \t\t\t<img  src=\"images/footer.jpg\">\r\n    \t\t</td>\r\n    \t</tr>\r\n    </table>\r\n  </body>\r\n</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/view/jsp/login.jsp(82,11) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty param.session_expired}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"warn\">\r\n\t\t\t        Your session has expired.<br/>\r\n\t\t\t      </div>\r\n\t\t\t    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /WEB-INF/view/jsp/login.jsp(87,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty param.login_error}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"error\">\r\n\t\t\t        Your login attempt was not successful, try again.<br/><br/>\r\n\t\t\t        Reason: ");
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fif_005f1, _jspx_page_context))
          return true;
        out.write(".\r\n\t\t\t      </div>\r\n\t\t\t    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /WEB-INF/view/jsp/login.jsp(90,19) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${SPRING_SECURITY_LAST_EXCEPTION.message}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /WEB-INF/view/jsp/login.jsp(100,30) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("j_spring_security_check");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent(null);
    // /WEB-INF/view/jsp/login.jsp(106,87) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty param.login_error}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fif_005f2, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f2);
    // /WEB-INF/view/jsp/login.jsp(106,131) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${SPRING_SECURITY_LAST_USERNAME}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }
}
