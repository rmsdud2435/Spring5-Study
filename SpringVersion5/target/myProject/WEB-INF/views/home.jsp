<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>spring-mvc-showcase</title>
	<link href="<c:url value="/resources/form.css" />" rel="stylesheet"  type="text/css" />		
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.core.css" />" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.theme.css" />" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.tabs.css" />" rel="stylesheet" type="text/css"/>
	
	<!--
		Used for including CSRF token in JSON requests
		Also see bottom of this file for adding CSRF token to JQuery AJAX requests
	-->
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<h1><a href="<c:url value="/" />">spring-mvc-showcase</a></h1>
<p>Recommended: Using a Web Developer tool such a Firebug to inspect the client/server interaction</p>
<div id="tabs">
	<ul>
		<li><a href="#simple">단순 데이터 가져오기</a></li>
		<li><a href="#mapping">@RestController를 통한 데이터 주고 받기</a></li>
		<li><a href="#data">Request Data</a></li>
		<li><a href="#responses">다양한 Response 만들기</a></li>
		<li><a href="#messageconverters">Content-type 변환하기</a></li>
		<li><a href="#views">View 이동하기</a></li>
		<li><a href="#convert">Type Conversion</a></li>
		<li><a href="#validation">Data 검증하기</a></li>
		<li><a href="<c:url value="/form" />" title="forms">Forms</a></li>
		<li><a href="<c:url value="/fileupload" />" title="fileupload">File Upload</a></li>
		<li><a href="#exceptions">Exception Handling</a></li>
		<li><a href="#redirect">Redirecting 해보기</a></li>
        <li><a href="#async">Async Requests</a></li>
    </ul>
    <div id="simple">
		<h2>단순 데이터 가져오기</h2>
		<ul>
			<li>
				<a id="simpleLink" class="textLink" href="<c:url value="/simple" />">단순히 값가져오기</a>
			</li>
			<li>
				<a id="simpleRevisited" class="textLink" href="<c:url value="/simple/revisited" />">Request헤더에 속성추가하여 가져오기</a>
			</li>
		</ul>
	</div>
	<div id="mapping">
		<h2>Request Mapping</h2>
		<p>
			See the <code>org.springframework.samples.mvc.mapping</code> package for the @Controller code
		</p>
		<ul>
			<li>
				<a id="byPath" class="textLink" href="<c:url value="/mapping/path" />">문자열 데이터 받기</a>
			</li>
			<li>
				<a id="byPathPattern" class="textLink" href="<c:url value="/mapping/path/helloworld" />">/mapping/path/ 포함한 url 매핑시키기</a>
			</li>
			<li>
				<a id="byParameter" class="textLink" href="<c:url value="/mapping/parameter?apple=banana" />">parameter로  url매핑 제한하기1</a>
			</li>
			<li>
				<a id="byNotParameter" class="textLink" href="<c:url value="/mapping/parameter?banana=cheese" />">parameter로  url매핑 제한하기2</a>
			</li>
			<li>
				<a id="byHeader" href="<c:url value="/mapping/header" />">헤더에 특정 Param추가하여 url제한하기1</a>
			</li>
			<li>
				<a id="byHeaderNegation" class="textLink" href="<c:url value="/mapping/header" />">헤더에 특정 Param추가하여 url제한하기2</a>
			</li>
			<li>
				<form id="byConsumes" class="readJsonForm" action="<c:url value="/mapping/consumes" />" method="post">
					<input id="byConsumesSubmit" type="submit" value="By consumes" />
				</form>
			</li>
			<li>
				<a id="byProducesAcceptJson" class="writeJsonLink" href="<c:url value="/mapping/produces" />">By produces via Accept=application/json</a>
			</li>
            <li>
                <a id="byProducesAcceptXml" class="writeXmlLink" href="<c:url value="/mapping/produces" />">By produces via Accept=appilcation/xml</a>
            </li>
            <li>
              <a id="byProducesJsonExt" class="writeJsonLink" href="<c:url value="/mapping/produces.json" />">By produces via ".json"</a>
            </li>
            <li>
                <a id="byProducesXmlExt" class="writeXmlLink" href="<c:url value="/mapping/produces.xml" />">By produces via ".xml"</a>
            </li>
		</ul>
	</div>
	<div id="data">
		<h2>Request Data</h2>
		<p>
			See the <code>org.springframework.samples.mvc.data</code> package for the @Controller code
		</p>
		<ul>
			<li>
				<a id="param" class="textLink" href="<c:url value="/data/param?foo=bar" />">Query parameter</a>
			</li>
			<li>
				<a id="group" class="textLink" href="<c:url value="/data/group?param1=foo&param2=bar&param3=baz" />">Group of query parameters</a>
			</li>
			<li>
				<a id="var" class="textLink" href="<c:url value="/data/path/foo" />">Path variable</a>
			</li>
			<li>
				<a id="matrixVar" class="textLink" href="<c:url value="/data/matrixvars;foo=bar/simple" />">Matrix variable</a>
			</li>
			<li>
				<a id="matrixVarMultiple" class="textLink" href="<c:url value="/data/matrixvars;foo=bar1/multiple;foo=bar2" />">Matrix variables (multiple)</a>
			</li>
			<li>
				<a id="header" class="textLink" href="<c:url value="/data/header" />">Header</a>
			</li>
			<li>
				<form id="requestBody" class="textForm" action="<c:url value="/data/body" />" method="post">
					<input id="requestBodySubmit" type="submit" value="Request Body" />
				</form>
			</li>				
			<li>
				<form id="requestBodyAndHeaders" class="textForm" action="<c:url value="/data/entity" />" method="post">
					<input id="requestBodyAndHeadersSubmit" type="submit" value="Request Body and Headers" />
				</form>
			</li>
		</ul>	
		<div id="standardArgs">
			<h3>Standard Resolvable Web Arguments</h3>
			<p>
			    See the <code>org.springframework.samples.mvc.data.standard</code> package for the @Controller code
			</p>
			<ul>
				<li>
					<a id="request" class="textLink" href="<c:url value="/data/standard/request" />">Request arguments</a>				
				</li>
				<li>
					<form id="requestReader" class="textForm" action="<c:url value="/data/standard/request/reader" />" method="post">
						<input id="requestReaderSubmit" type="submit" value="Request Reader" />
					</form>
				</li>			
				<li>
					<form id="requestIs" class="textForm" action="<c:url value="/data/standard/request/is" />" method="post">
						<input id="requestIsSubmit" type="submit" value="Request InputStream" />
					</form>
				</li>
				<li>
					<a id="response" class="textLink" href="<c:url value="/data/standard/response" />">Response arguments</a>				
				</li>			
				<li>
					<a id="writer" class="textLink" href="<c:url value="/data/standard/response/writer" />">Response Writer</a>
				</li>
				<li>
					<a id="os" class="textLink" href="<c:url value="/data/standard/response/os" />">Response OutputStream</a>				
				</li>
				<li>
					<a id="session" class="textLink" href="<c:url value="/data/standard/session" />">Session</a>			
				</li>			
			</ul>
		</div>
		<div id="customArgs">
			<h3>Custom Resolvable Web Arguments</h3>	
			<p>
				See the <code>org.springframework.samples.mvc.data.custom</code> package for the @Controller code
			</p>
			<ul>
				<li>
					<a id="customArg" class="textLink" href="<c:url value="/data/custom" />">Custom</a>			
				</li>
			</ul>
		</div>
	</div>
	<div id="responses">
		<h2>다양한 Response 만들기</h2>		
		<ul>
			<li>
				<a id="responseBody" class="textLink" href="<c:url value="/response/annotation" />">responseBody를 통한 단순 String값 리턴하기</a>			
			</li>
			<li>
				<a id="responseCharsetAccept" class="utf8TextLink" href="<c:url value="/response/charset/accept" />">JSP에서 호출시 UTF-8 인코딩 세팅하기</a>
			</li>
			<li>
				<a id="responseCharsetProduce" class="textLink" href="<c:url value="/response/charset/produce" />">Controller에서 호출시 UTF-8 인코딩 세팅하기</a>
			</li>
			<li>
				<a id="responseEntityStatus" class="textLink" href="<c:url value="/response/entity/status" />">ResponseEntity를 통한 에러값 받기</a>			
			</li>
			<li>
				<a id="responseEntityHeaders" class="textLink" href="<c:url value="/response/entity/headers" />">ResponseEntity를 통하여 헤더값 내려주기</a>			
			</li>
		</ul>	
	</div>
	<div id="messageconverters">
		<h2>Content-type 변환하기</h2>
		<div id="stringMessageConverter">
			<h3>text/plain으로 주고 받기</h3>
			<ul>
				<li>
					<!-- form의 클래스를 같이 쓰려고 이렇게 코딩하였지만 보편적으로 input에다가 name속성으로 받을 변수명 선언 후에 $("#form.textForm).submit()으로 쓰인다. -->
					<form id="readString" class="textForm" action="<c:url value="/messageconverters/string" />" method="post">
						<input id="readStringSubmit" type="submit" value="Post로 데이터 보내기" />
					</form>
				</li>
				<li>
					<a id="writeString" class="textLink" href="<c:url value="/messageconverters/string" />">Get으로 데이터 보내기</a>
				</li>
			</ul>
			<h3>application/x-www-form-urlencoded으로 주고 받기</h3>
			<ul>
				<li>
					<form id="readForm" action="<c:url value="/messageconverters/form" />" method="post">
						<input id="readFormSubmit" type="submit" value="application/x-www-form-urlencoded으로 데이터 보내기" />		
					</form>
				</li>
				<li>
					<a id="writeForm" href="<c:url value="/messageconverters/form" />">MultiValueMap 자료형 이용하여 application/x-www-form-urlencoded으로 데이터 받기</a>
				</li>
			</ul>
			<h3>application/xml으로 데이터 주고 받기</h3>
			<ul>
				<li>
					<form id="readXml" class="readXmlForm" action="<c:url value="/messageconverters/xml" />" method="post">
						<input id="readXmlSubmit" type="submit" value="POST형식으로 application/xml 주고 받기" />		
					</form>
				</li>
				<li>
					<a id="writeXmlAccept" class="writeXmlLink" href="<c:url value="/messageconverters/xml" />">GET형식으로  Accept=application/xml 주고받기</a>
				</li>
                <li>
                    <a id="writeXmlExt" class="writeXmlLink" href="<c:url value="/messageconverters/xml.xml" />">컨트롤러에 .xml 붙여서 전달하기</a>
                </li>
			</ul>
			<h3>application/json으로 데이터 주고 받기</h3>
			<ul>
				<li>
					<form id="readJson" class="readJsonForm" action="<c:url value="/messageconverters/json" />" method="post">
						<input id="readJsonSubmit" type="submit" value="POST형식으로 VO를 이용하여 application/json형태 주고받기 " />	
					</form>
				</li>
				<li>
					<form id="readJsonInvalid" class="readJsonForm invalid" action="<c:url value="/messageconverters/json" />" method="post">
						<input id="readInvalidJsonSubmit" type="submit" value="@Valid 만족시키지 않게하여 400에러  만들기" />	
					</form>
				</li>
				<li>
					<a id="writeJsonAccept" class="writeJsonLink" href="<c:url value="/messageconverters/json" />">Client에서 헤더에  Accept = application/json 추가하여 보내기</a>
				</li>
                <li>
                    <a id="writeJsonExt" class="writeJsonLink" href="<c:url value="/messageconverters/json.json" />">호출 URL에 .json을 붙여 application/json로 보내기</a>
                </li>
			</ul>
			<h3>application/atom+xml으로 데이터 주고 받기</h3>
			<ul>
				<li>
					<form id="readAtom" action="<c:url value="/messageconverters/atom" />" method="post">
						<input id="readAtomSubmit" type="submit" value="POST형식으로 Feed변수를 통하여 application/atom+xml으로 데이터 주고 받기" />		
					</form>
				</li>
				<li>
					<a id="writeAtom" href="<c:url value="/messageconverters/atom" />">GET형식으로 ATOM 문서 받아오기</a>
				</li>
			</ul>
			<h3>application/rss+xml으로 데이터 주고 받기</h3>
			<ul>
				<li>
					<form id="readRss" action="<c:url value="/messageconverters/rss" />" method="post">
						<input id="readRssSubmit" type="submit" value="POST형식으로 Channel변수를 통하여 application/rss+xml으로 데이터 주고 받기" />	
					</form>
				</li>
				<li>
					<a id="writeRss" href="<c:url value="/messageconverters/rss" />">Write Rss</a>
				</li>
			</ul>		
		</div>
	</div>
	<div id="views">
		<h2>View 랜더링 해보기</h2>
		<ul>
			<li>
				<a href="<c:url value="/views/html" />">return을 통한 페이지 매핑</a>
			</li>
		</ul>	
		<ul>
			<li>
				<a href="<c:url value="/views/viewName" />">void를 통한 페이지 매핑</a>
			</li>
		</ul>	
		<ul>
			<li>
				<a href="<c:url value="/views/pathVariables/gykim/19931119" />">rest방식을 이용한 페이지 매핑</a>
			</li>
		</ul>
		<ul>
			<li>
				<a href="<c:url value="/views/dataBinding/gykim/19931119" />">javax.validation어노태이션을 이용한 매핑</a>
			</li>
		</ul>
	</div>
	<div id="convert">
		<h2>Type Conversion</h2>
		<p>
			See the <code>org.springframework.samples.mvc.convert</code> package for the @Controller code
		</p>
		<ul>
			<li>
				<a id="primitive" class="textLink" href="<c:url value="/convert/primitive?value=3" />">Primitive</a>
			</li>
			<li>
				<a id="date" class="textLink" href="<c:url value="/convert/date/2010-07-04" />">Date</a>
			</li>
			<li>
				<a id="collection" class="textLink" href="<c:url value="/convert/collection?values=1&values=2&values=3&values=4&values=5" />">Collection 1 (multi-value parameter)</a>
			</li>
			<li>
				<a id="collection2" class="textLink" href="<c:url value="/convert/collection?values=1,2,3,4,5" />">Collection 2 (single comma-delimited parameter value)</a>
			</li>
			<li>
				<a id="formattedCollection" class="textLink" href="<c:url value="/convert/formattedCollection?values=2010-07-04,2011-07-04" />">@Formatted Collection</a>
			</li>		
			<li>
				<a id="valueObject" class="textLink" href="<c:url value="/convert/value?value=123456789" />">Custom Value Object</a>
			</li>
			<li>
				<a id="customConverter" class="textLink" href="<c:url value="/convert/custom?value=123-45-6789" />">Custom Converter</a>
			</li>		
		</ul>
		<div id="convert-bean">
			<h3>JavaBean Property Binding</h3>
			<ul>
				<li>
					<a id="primitiveProp" class="textLink" href="<c:url value="/convert/bean?primitive=3" />">Primitive</a>
				</li>	
				<li>
					<a id="dateProp" class="textLink" href="<c:url value="/convert/bean?date=2010-07-04" />">Date</a>
				</li>	
				<li>
					<a id="maskedProp" class="textLink" href="<c:url value="/convert/bean?masked=(205) 333-3333" />">Masked</a>
				</li>	
				<li>
					<a id="listProp" class="textLink" href="<c:url value="/convert/bean?list[0]=1&list[1]=2&list[2]=3" />">List Elements</a>
				</li>
				<li>
					<a id="formattedListProp" class="textLink" href="<c:url value="/convert/bean?formattedList[0]=2010-07-04&formattedList[1]=2011-07-04" />">@Formatted List Elements</a>
				</li>
				<li>
					<a id="mapProp" class="textLink" href="<c:url value="/convert/bean?map[0]=apple&map[1]=pear" />">Map Elements</a>
				</li>
				<li>
					<a id="nestedProp" class="textLink" href="<c:url value="/convert/bean?nested.foo=bar&nested.list[0].foo=baz&nested.map[key].list[0].foo=bip" />">Nested</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="validation">
		<h2>Data 검증하기</h2>
		<ul>
			<li>
				<a id="validateNoErrors" class="textLink" href="<c:url value="/validate?number=3&date=2029-07-04" />">검증 성공하기</a>
			</li>
			<li>
				<a id="validateErrors" class="textLink" href="<c:url value="/validate?number=3&date=2010-07-01" />">검증 실패하기</a>
			</li>
		</ul>	
	</div>
	<div id="exceptions">
		<h2>Exception Handling</h2>
		<p>
			See the <code>org.springframework.samples.mvc.exceptions</code> package for the @Controller code	
		</p>
		<ul>
			<li>
				<a id="exception" class="textLink" href="<c:url value="/exception" />">@ExceptionHandler in Controller</a>
			</li>
			<li>
				<a id="globalException" class="textLink" href="<c:url value="/global-exception" />">Global @ExceptionHandler</a>
			</li>
		</ul>
	</div>
	<div id="redirect">
		<h2>Redirecting 해보기</h2>
		<ul>
			<li>
				<a href="<c:url value="/redirect/uriTemplate" />">RedirectAttributes 클래스를 이용한 redirecting</a>
			</li>
			<li>
				<a href="<c:url value="/redirect/uriComponentsBuilder" />">UriComponentsBuilder 클래스를 이용한 redirecting</a>
			</li>
		</ul>
	</div>
	<div id="async">
		<h2>Async Requests</h2>
		<p>
			<em>Note: Links may take 2-3 seconds to complete.</em>
		</p>
		<p>
		  See the <code>org.springframework.samples.mvc.async</code> package for the @Controller code.
		</p>
		<ul>
		<li>
			<a id="callableResponseBodyLink" class="textLink"
				href="<c:url value="/async/callable/response-body" />">GET /async/callable/response-body</a>
		</li>
		<li>
			<a id="callableViewLink" class="textLink"
				href="<c:url value="/async/callable/view" />">GET /async/callable/view</a>
		</li>
		<li>
			<a id="callableExceptionLink" class="textLink"
				href="<c:url value="/async/callable/exception" />">GET /async/callable/exception</a>
		</li>
		<li>
			<a id="callableUnhandledExceptionLink" class="textLink"
				href="<c:url value="/async/callable/exception?handled=false" />">GET /async/callable/exception?handled=false</a>
				(500 Error expected)
		</li>
		<li>
			<a id="callableCustomTimeoutLink" class="textLink"
				href="<c:url value="/async/callable/custom-timeout-handling" />">GET /async/callable/custom-timeout-handling</a>
		</li>
		<li>
			<a id="deferredResultSuccessLink" class="textLink"
				href="<c:url value="/async/deferred-result/response-body" />">GET /async/deferred-result/response-body</a>
		</li>
		<li>
			<a id="deferredResultModelAndViewLink" class="textLink"
				href="<c:url value="/async/deferred-result/model-and-view" />">GET /async/deferred-result/model-and-view</a>
		</li>
		<li>
			<a id="deferredResultErrorLink" class="textLink"
				href="<c:url value="/async/deferred-result/exception" />">GET /async/deferred-result/exception</a>
		</li>
		<li>
			<a id="deferredResultTimeoutValueLink" class="textLink"
				href="<c:url value="/async/deferred-result/timeout-value" />">GET /async/deferred-result/timeout-value</a>
		</li>
		</ul>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryform/2.8/jquery.form.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.core.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.widget.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.tabs.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json2.js" />"></script>
<script>
	MvcUtil = {};
	MvcUtil.showSuccessResponse = function (text, element) {
		MvcUtil.showResponse("success", text, element);
	};
	MvcUtil.showErrorResponse = function showErrorResponse(text, element) {
		MvcUtil.showResponse("error", text, element);
	};
	MvcUtil.showResponse = function(type, text, element) {
		var responseElementId = element.attr("id") + "Response";
		var responseElement = $("#" + responseElementId);
		if (responseElement.length == 0) {
			responseElement = $('<span id="' + responseElementId + '" class="' + type + '" style="display:none">' + text + '</span>').insertAfter(element);
		} else {
			responseElement.replaceWith('<span id="' + responseElementId + '" class="' + type + '" style="display:none">' + text + '</span>');
			responseElement = $("#" + responseElementId);
		}
		responseElement.fadeIn("slow");
	};
	MvcUtil.xmlencode = function(xml) {
		//for IE 
		var text;
		if (window.ActiveXObject) {
		    text = xml.xml;
		 }
		// for Mozilla, Firefox, Opera, etc.
		else {
		   text = (new XMLSerializer()).serializeToString(xml);
		}			
		    return text.replace(/\&/g,'&'+'amp;').replace(/</g,'&'+'lt;')
	        .replace(/>/g,'&'+'gt;').replace(/\'/g,'&'+'apos;').replace(/\"/g,'&'+'quot;');
	};
</script>	
<script type="text/javascript">
$(document).ready(function() {
	$("#tabs").tabs();

	// Append '#' to the window location so "Back" returns to the selected tab
	// after a redirect or a full page refresh (e.g. Views tab).

	// However, note this general disclaimer about going back to previous tabs: 
	// http://docs.jquery.com/UI/API/1.8/Tabs#Back_button_and_bookmarking

	$("#tabs").bind("tabsselect", function(event, ui) { window.location.hash = ui.tab.hash; });


	$("a.textLink").click(function(){
		var link = $(this);
		$.ajax({ url: link.attr("href"), dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, link); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, link); }});
		return false;
	});

	$("a.utf8TextLink").click(function(){
		var link = $(this);
		$.ajax({ url: link.attr("href"), dataType: "text", beforeSend: function(req) { req.setRequestHeader("Accept", "text/plain;charset=UTF-8"); }, success: function(text) { MvcUtil.showSuccessResponse(text, link); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, link); }});
		return false;
	});

 	$("form.textForm").submit(function(event) {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), data: "김근영", contentType: "text/plain", dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, button); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, button); }});
		return false;
	}); 
	
 	//결국 ajax통신에서는 contentType을 application/x-www-form-urlencoded 것 말고는 text/plain과 동일
	$("#readForm").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), data: "name=김근영&birth=1993.11.19", contentType: "application/x-www-form-urlencoded", dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, button); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, button); }});
		return false;
	});

	$("#writeForm").click(function() {
		var link = $(this);
		$.ajax({ url: this.href, dataType: "text", beforeSend: function(req) { req.setRequestHeader("Accept", "application/x-www-form-urlencoded"); }, success: function(form) { MvcUtil.showSuccessResponse(form, link); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, link); }});					
		return false;
	});

	$("form.readXmlForm").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), data: "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><javaBean><name>김근영</name><birth>1993.11.19</birth></javaBean>", contentType: "application/xml", dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, button); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, button); }});
		return false;
	});

	$("a.writeXmlLink").click(function() {
		var link = $(this);
		$.ajax({ url: link.attr("href"),
			beforeSend: function(req) { 
				if (!this.url.match(/\.xml$/)) {
					req.setRequestHeader("Accept", "application/xml");
				}
			},
			success: function(xml) {
				MvcUtil.showSuccessResponse(MvcUtil.xmlencode(xml), link);
			},
			error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, link);
			}
		});
		return false;
	});					

	$("form.readJsonForm").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		var data = form.hasClass("invalid") ?
				"{ \"name\": \"김근영\" }" : 
				"{ \"name\": \"김근영\", \"birth\": \"1993.11.19\" }";
		$.ajax({ type: "POST", url: form.attr("action"), data: data, contentType: "application/json", dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, button); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, button); }});
		return false;
	});

	$("a.writeJsonLink").click(function() {
		var link = $(this);
		$.ajax({ url: this.href,
			beforeSend: function(req) {
				if (!this.url.match(/\.json$/)) {
					req.setRequestHeader("Accept", "application/json");
				}
			},
			success: function(json) {
				MvcUtil.showSuccessResponse(JSON.stringify(json), link);
			},
			error: function(xhr) {
				MvcUtil.showErrorResponse(xhr.responseText, link);
			}});
		return false;
	});

	$("#readAtom").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), data: '<?xml version="1.0" encoding="UTF-8"?> <feed xmlns="http://www.w3.org/2005/Atom"><title>ATOM문서 형식의 제목입니다.</title></feed>', contentType: "application/atom+xml", dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, button); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, button); }});
		return false;
	});

	$("#writeAtom").click(function() {
		var link = $(this);
		$.ajax({ url: link.attr("href"),
			beforeSend: function(req) { 
				req.setRequestHeader("Accept", "application/atom+xml");
			},
			success: function(feed) {
				MvcUtil.showSuccessResponse(MvcUtil.xmlencode(feed), link);
			},
			error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, link);
			}
		});
		return false;
	});
	
	$("#readRss").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), data: '<?xml version="1.0" encoding="UTF-8"?> <rss version="2.0"><channel><title>김근영의 RSS 문서 제목</title></channel></rss>', contentType: "application/rss+xml", dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, button); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, button); }});
		return false;
	});

	$("#writeRss").click(function() {
		var link = $(this);	
		$.ajax({ url: link.attr("href"),
			beforeSend: function(req) { 
				req.setRequestHeader("Accept", "application/rss+xml");
			},
			success: function(feed) {
				MvcUtil.showSuccessResponse(MvcUtil.xmlencode(feed), link);
			},
			error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, link);
			}
		});
		return false;
	});

	$("#byHeader").click(function(){
		var link = $(this);
		$.ajax({ url: this.href, dataType: "text", beforeSend: function(req) { req.setRequestHeader("SecretHeader", "apple"); }, success: function(form) { MvcUtil.showSuccessResponse(form, link); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, link); }});
		return false;
	});

	// Include CSRF token as header in JQuery AJAX requests
	// See http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-include-csrf-token-ajax
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

});
</script>
</body>
</html>
