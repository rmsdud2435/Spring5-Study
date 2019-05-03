package com.myproject.spring5.mvc.messageconverters;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController와 @Controller의 육안으로 보이는 차이점은 @ResponseBody의 유무와 requestUrl.xml, requestUrl.json이 자동을 requestUrl에 매핑이 되며 그에 따른 데이터 형식으로 보여준다.
@RestController
@RequestMapping("/messageconverters")
public class MessageConvertersController {

	// Post형식으로 데이터 받기. 
	@PostMapping(value = "/string", produces = "text/plain;charset=UTF-8")
	public String readString(@RequestBody String string) throws UnsupportedEncodingException {
		return "Post형식으로 데이터 받았습니다. 받은 데이터는 '" + string + "'입니다.";
	}

	// Get형식으로 통신 받기
	@GetMapping(value = "/string", produces = "text/plain;charset=UTF-8")
	public String writeString() {
		return "Get형식으로 데이터 받았습니다.";
	}

	/*
	 * Content-Type에는 Multipart, Application, audio, text 등으로 Type을 정하고
	 * xml, javascript, x-www-form-urlencode, javascript, plain, text등 form형태를 정하여 지정한다.
	 * 
	 * x-www-form-urlencode와 multipart/form-data은 둘다 폼 형태이지만 
	 * x-www-form-urlencode은 대용량 바이너리 테이터를 전송하기에 비능률적이기 때문에 대부분 첨부파일은 multipart/form-data를 사용하게 된다
	 */
	@PostMapping(value = "/form", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String readForm(@ModelAttribute JavaBean bean) {
		return "Read x-www-form-urlencoded: " + bean;
	}

	//해당 자료형으로 내려줄때 계속 UTF-8로 인코딩되어 내려가서 디코딩을 못해 %EA%B9%80%EA%B7%BC%EC%98%81와 같은 값이 내려온다. 디코딩할 방법 알아야 할듯
	@GetMapping("/form")
	public MultiValueMap<String, String> writeForm() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", "김근영");
		map.add("birth", "1993.11.19");
		return map;
	}

	@PostMapping(value = "/xml", produces = "application/xml;charset=UTF-8")
	public String readXml(@RequestBody JavaBean bean) {
		return "Read from XML: " + bean;
	}

	//객체를 선언하고 application/xml로 보내면 자동으로 xml형식에 맞게 전달한다.
	@GetMapping("/xml")
	public JavaBean writeXml() {
		return new JavaBean("김근영", "1993.11.19");
	}

	//객체를 선언하고 application/json으로 보내면 자동으로 json형식에 맞게 전달한다.
	@PostMapping(value = "/json", produces = "application/json;charset=UTF-8")
	// @Valid 조건에 만족하지 못하면 400Error발생
	public String readJson(@Valid @RequestBody JavaBean bean) {
		
		//이렇게 선언하면 bean객체가 toString()이 자동으로 되네
		return "Read from JSON: " + bean;
	}

	//Get으로 받으면 서버에서 다시 utf-8신경안 써도 되는 듯
	@GetMapping("/json")
	public JavaBean writeJson() {
		return new JavaBean("김근영", "1993.11.19");
	}

	// atom 문서를 읽기 최적화된 Feed 자료형을 이용하여 데이터를 받는다.
	@PostMapping(value = "/atom", produces = "application/atom+xml;charset=UTF-8")
	public String readFeed(@RequestBody Feed feed) {
		return feed.getTitle();
	}

	@GetMapping("/atom")
	public Feed writeFeed() {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("김근영의 Atom 문서 제목");
		return feed;
	}

	// rss 문서를 읽기 최적화된 Channel 자료형을 이용하여 데이터를 받는다.
	@PostMapping(value = "/rss", produces = "application/rss+xml;charset=UTF-8")
	public String readChannel(@RequestBody Channel channel) {
		return channel.getTitle();
	}

	// Channel 자료형을 통해 rss 문서값 세팅
	@GetMapping("/rss")
	public Channel writeChannel() {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		channel.setTitle("김근영의 RSS 문서 제목");
		channel.setDescription("Description");
		channel.setLink("http://localhost:8080/mvc-showcase/rss");
		return channel;
	}

}