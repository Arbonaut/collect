package jsp.WEB_002dINF.view.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">\t\r\n    <head>\r\n        <title>Open Foris Collect</title>\r\n    \t<meta http-equiv=\"cache-control\" content=\"no-store, no-cache, must-revalidate\" />\r\n\t\t<meta http-equiv=\"Pragma\" content=\"no-store, no-cache\" />\r\n\t\t<meta http-equiv=\"Expires\" content=\"0\" />\r\n\t\t<meta name=\"google\" value=\"notranslate\" />         \r\n        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\t\t\r\n        <style type=\"text/css\" media=\"screen\"> \r\n\t\t\thtml, body\t{ height:100%; }\r\n\t\t\tbody {\r\n\t\t\t\tmargin:0;\r\n\t\t\t\tpadding:0;\r\n\t\t\t\toverflow: hidden;\r\n\t\t\t\ttext-align:center;\r\n\t\t\t    background-color: #ffffff;\r\n\t\t\t}\r\n\t\t\tobject:focus {\r\n\t\t\t\toutline:none;\r\n\t\t\t}\r\n        </style>\r\n        \r\n        <script type=\"text/javascript\" src=\"script/jquery-1.6.2.min.js\"></script>\r\n        <script type=\"text/javascript\" src=\"script/swfobject.js\"></script>\r\n        <script type=\"text/javascript\" src=\"script/openforis.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\">\r\n            // For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. \r\n            var swfVersionStr = \"10.2.0\";\r\n            // To use express install, set to playerProductInstall.swf, otherwise the empty string. \r\n            var xiSwfUrlStr = \"flash/playerProductInstall.swf\";\r\n            var flashvars = {\r\n            \tlang: 'en_US'\r\n            };\r\n            var params = {};\r\n           // params.wmode = \"opaque\";\r\n            params.quality = \"high\";\r\n            params.bgcolor = \"#ffffff\";\r\n            params.allowscriptaccess = \"sameDomain\";\r\n            params.allowfullscreen = \"true\";\r\n            var attributes = {};\r\n            attributes.id = \"collect\";\r\n            attributes.name = \"collect\";\r\n            attributes.align = \"middle\";\r\n            swfobject.embedSWF(\r\n                \"collect.swf\", \"flashContent\", \r\n                \"100%\", \"100%\", \r\n                swfVersionStr, xiSwfUrlStr, \r\n                flashvars, params, attributes);\r\n");
      out.write("            // JavaScript enabled so display the flashContent div in case it is not replaced with a swf object.\r\n            swfobject.createCSS(\"#flashContent\", \"display:block;text-align:left;\");\r\n            \r\n            //init OpenForis\r\n            OPENFORIS.init();\r\n        </script>\r\n        \r\n    </head>\r\n    <body>\r\n\t\t<!-- SWFObject's dynamic embed method replaces this alternative HTML content with Flash content when enough \r\n             JavaScript and Flash plug-in support is available. The div is initially hidden so that it doesn't show\r\n             when JavaScript is disabled.\r\n        -->\r\n        <div id=\"flashContent\">\r\n            <p>\r\n                To view this page ensure that Adobe Flash Player version \r\n                10.2.0 or greater is installed. \r\n            </p>\r\n            <script type=\"text/javascript\"> \r\n                var pageHost = ((document.location.protocol == \"https:\") ? \"https://\" : \"http://\"); \r\n                document.write(\"<a href='http://www.adobe.com/go/getflashplayer'><img src='\" \r\n");
      out.write("                                + pageHost + \"www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>\" ); \r\n            </script> \r\n        </div>\r\n        \r\n        <noscript>Please enable JavaScript in your browser.</noscript>\r\n   </body>\r\n</html>");
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
}
