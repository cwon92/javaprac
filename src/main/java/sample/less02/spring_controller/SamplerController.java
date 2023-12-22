package sample.less02.spring_controller;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;
import sample.less02.spring_controller.domain.SampleDTO;
import sample.less02.spring_controller.domain.SampleDTOList;
import sample.less02.spring_controller.domain.TodoDTO;

@Controller
@RequestMapping(value = "/sample/*")
@Log4j
public class SamplerController {
	
	//1. 스프링 컨트롤러 클래스의 메서드의 @RequestMapping, @GetMappling, @PostMapping
	//   어노테이션으로 urlPattern 을 지정하는 방법을 실습합니다.

//	@RequestMapping(value = "/sample/1", method = RequestMethod.GET)
//	public void basic1() {
//		log.info("basic1===================");
//	} //웹브라우저: http://localhost:8080/mypro00/sample/1
//	
//	@RequestMapping(value = "/sample/2", method = RequestMethod.GET)
//	public void basic2() {
//		log.info("basic2===================");
//	} //웹브라우저: http://localhost:8080/mypro00/sample/2
	
	@RequestMapping(value = "/1", method = RequestMethod.GET)
	public void basic1() {
		log.info("basic1===================");
	} //웹브라우저: http://localhost:8080/mypro00/sample/1
	
	@RequestMapping(value = "/2", method = RequestMethod.GET)
	public void basic2() {
		log.info("basic2===================");
	} //웹브라우저: http://localhost:8080/mypro00/sample/2
	
	@RequestMapping(value = "/3", method = {RequestMethod.GET, RequestMethod.POST})
	public void basic3() {
		log.info("basic3===================");
	} //웹브라우저: http://localhost:8080/mypro00/sample/3
	
	///////////////////////////////////////////////////////////////////////////////
	@GetMapping(value = "/baiscOnlyGet")
	public void basicGet() {
		log.info("basicGet===================");
		System.out.println("basicGet====================");
	}
	//웹브라우저: http://localhost:8080/mypro00/sample/basicOnlyGet
	@PostMapping(value = "/baiscOnlyPost")
	public void basicPost() {
		log.info("basicPost===================");
		System.out.println("basicPost====================");
	}
	 //웹브라우저: http://localhost:8080/mypro00/sample/basicOnlyPost
     // ==> 오류
	 //메시지 Request method 'GET' not supported
	//설명: 요청 행에 포함된 해당 메소드는, origin 서버에 의해 인지되었으나, 대상 리소스에 의해 지원되지 않습니다.
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	//2. 스프링 컨트롤러 클래스의 메서드의 매개변수 동작 방법 및 사용법을 실습합니다.
	
	//SampleDTO 타입의 객체를 자동으로 생성해서 객체의 필드에 
	//브라우저로부터 전달된 요청에 포함되어 있는 값들을 저장합니다.
	@GetMapping(value = "/ex01")
	public void myEx01(SampleDTO dto) {
		System.out.println("dto.name: " + dto.getName());
		System.out.println("dto.age: " + dto.getAge());
		System.out.println("=================" + dto);
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex01?name=홍길동&age=24 로 요청
	
	@GetMapping(value = "/ex02")
	public void myEx02(String name, int age) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);		

	} //웹브라우저: http://localhost:8080/mypro00/sample/ex02?name=홍길동&age=24 로 요청
	
	@GetMapping(value = "/ex022")  //url에 전달된 값의 매개변수 이름과 메서드이 매개변수 이름이 다를 때
								   //@RequestParam 어노테이션으로 url의 매개변수 이름을 설정해서
								   //매개변수에 값을 전달할 수 있습니다..
	public void myEx022(@RequestParam("name") String name1,
			            @RequestParam("age") Integer age1) {
		System.out.println("name1: " + name1);
		System.out.println("age1: " + age1);		

	} //웹브라우저: http://localhost:8080/mypro00/sample/ex022?name=홍길동&age=24 로 요청
	
	@GetMapping(value = "/ex02List") //값 전달이 않됨
	public void myEx02List(ArrayList<String> myIds) {
		System.out.println("=========myIds: " +  myIds);
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex02List?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨 요청
	
	@GetMapping(value = "/ex022List") //값 전달이 됨
	public void myEx022List(@RequestParam ("myIds") ArrayList<String> myIds) {
		System.out.println("=========myIds.size(): " +  myIds.size());
		System.out.println("=========myIds: " +  myIds);
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex022List?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨 요청
	
	
	@GetMapping(value = "/ex02Array") //값 전달이 됨
	public void myEx02Array(String[] myIds) {
		System.out.println("=========myIds.length: " +  myIds.length);
		System.out.println("=========myIds: " + Arrays.toString(myIds));
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex02Array?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨 요청
	
	@GetMapping(value = "/ex022Array") //값 전달이 됨
	public void myEx022Array(@RequestParam("myIds") String[] myIds) {
		System.out.println("=========myIds.length: " +  myIds.length);
		System.out.println("=========myIds: " + Arrays.toString(myIds));
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex022Array?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨 요청
	

	
	@GetMapping("ex02BeanList")
	public void ex2BeanList(SampleDTOList list) {
		System.out.println("=======list: " +  list);
	}//웹브라우저: http://localhost:8080/mypro00/sample/ex02BeanList?list[0].name=홍길동&list[1].name=슈퍼맨&list[2].age=24 요청
     //웹브라우저: http://localhost:8080/mypro00/sample/ex02BeanList?list%5B0%5D.name=홍길동&list%5B1%5D.name=슈퍼맨&list%5B2%5D.age=24 요청
     //[ : %5B, ] : %5D

	@GetMapping(value = "/ex03")
	public void ex03(TodoDTO todo) {
		System.out.println("todo: " + todo);
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex03?title=홍길동&dueDate=2023-05-28 요청
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//3. 스프링 컨트롤러 클래스의 메서드의 리턴타입 동작 방식 및 사용법을 실습합니다.
	//   스프링 컨트롤러 메서드의 리턴값은 JSP 페이지를 호출하는 역할을 합니다.
	
	//반환타입: void로 설정된 경우.
	@GetMapping("/ex04")
	public void ex04(SampleDTO dto, Integer page) {
		System.out.println("dto: " + dto);
		System.out.println("page: " + page);
		
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex04?name=홍길동&age=24&page=1 요청
	  //웹브라우저: http://localhost:8080/mypro00/sample/ex04?name=홍길동&age=24 요청
	  //브라우저에 표시된 오류 메시지를 확인
      //메시지: 파일 [/WEB-INF/views/sample/ex04.jsp]을(를) 찾을 수 없습니다.
      //사용자 요청으로 컨트롤러 메서드가 실행되는 중에
      //반환타입이 void 인 경우 컨트롤러의 메서드는 메서드에 지정된 URL 패턴에(예, /sample/ex04)
      //접두어로 /WEB-INF/views/를 붙이고, 접미어로 .jsp 붙여서
      //  /WEB-INF/views/sample/ex04.jsp 를 호출합니다.
	
	
	  //반환타입: String로 설정된 경우.
	  @GetMapping("/ex044")
	  public String ex044(SampleDTO dto, Integer page) {
		  System.out.println("dto: " + dto);
		  System.out.println("page: " + page);
		  return "ex04";
		  
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex044?name=홍길동&age=24&page=1 요청
	  //컨트롤러 메서드의 반환타입이 String인 경우, 특정 JSP파일의 이름을 지정하여 반환되도록 구성합니다.
	  //위의 예에서, 접두어로 /WEB-INF/views/ 붙이고, 접미어로 .jsp를 붙여서
	  //   /WEB-INF/views/ex04.jsp 파일을 결과화면으로 사용합니다
	  
	  //브라우저에 표시된 결과 JSP파일을 확인하면,
	  //브라우저에서 전달된 값은 컨트롤러 메서드의 매개변수에 자동저장되고,
	  //이 값들은 스프링 내부 동작에 의해서 JSP 파일까지 전달됩니다.
	  //단, 기본타입, Wrapper타입, String 타입 값은 전달되지 않습니다.
	  //컨트롤러 메서드의 기본타입, Wrapper, String 타입의 매개변수에 저장된 값을 JSP 파일로 전달하려면
      // @ModelAttribute 어노테이션을 이용합니다.
	  
	  @GetMapping("/ex05")
	  public String myEx05(SampleDTO dto, 
			  			   @ModelAttribute("page") Integer page) {
		  System.out.println("dto: " + dto);
		  System.out.println("page1: " + page);
		  return "sample/ex04";
		  
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex05?name=홍길동&age=24&page=33 요청	  

	  @GetMapping("/ex055")
	  public String myEx055(SampleDTO dto, Integer page, Model model) {
		  System.out.println("dto: " + dto);
		  System.out.println("page: " + page);
		  System.out.println("model: " + model);
		  
		  //model.addAttribute("smaple", dto);
		  model.addAttribute("page", page);
		  
		  return "sample/ex04";
		  
	} //웹브라우저: http://localhost:8080/mypro00/sample/ex055?name=홍길동&age=24&page=1 요청	
	  
	  @GetMapping(value = "/ex055List") //JSP 파일까지 값 전달이 안됨
	  public String myEx055List(@RequestParam ("myIds") ArrayList<String> myIds, 
			                    SampleDTO dto ,
			                    Model model) {
		  System.out.println("=========myIds.size(): " +  myIds.size());
		  System.out.println("=========myIds: " +  myIds);
		  System.out.println("=====dto: " + dto);
		  
		  model.addAttribute("myIds", myIds);
		  
		  return "sample/ex04" ;
		  
	  } //웹브라우저: http://localhost:8080/mypro00/sample/ex055List?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨&name=이순신&age=24 요청
	  
	  //잘못된 사용 예
	  @GetMapping(value = "/ex0555List") //JSP 파일까지 값이 하나만 전달됨
	  public String myEx0555List(@ModelAttribute ("myIds") ArrayList<String> myIds, 
			                    SampleDTO dto ) {
		  System.out.println("=========myIds.size(): " +  myIds.size());
		  System.out.println("=========myIds: " +  myIds);
		  System.out.println("=====dto: " + dto);
		  		  
		  return "sample/ex04" ;
		  
	  } //웹브라우저: http://localhost:8080/mypro00/sample/ex0555List?myIds=홍길동&myIds=슈퍼맨&myIds=베트맨&name=이순신&age=24 요청
	  
	  //컨트롤러 메서드의 반환타입이 String 이고, 반환문자열이 redirect:로 시작된 경우 실습
	  //컨트롤러 메서드의 매개변수의 타입은 기본타입 보다는 Wrapper 클래스로 객체타입으로 지정하는 것을 권장
	  //이유: 전달받은 값이 없을 경우, String에 의해서 null이 매개변수에 대입될 수 있도록. 
	  @GetMapping("/ex06")
	  public String myEx06(String name, Integer age, Integer page) {

		  System.out.println("name: " + name);
		  System.out.println("age: " + age);
		  System.out.println("page: " + page);
	      
		  try {
			name = URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		  //[주의]아래에서 redirect: 다음에 컨텍스트이름을 포함하면 않됨
		  return "redirect:/ex04.jsp?name=" + name + "&age=" + age + "&page=" + page ;
		  
	  }   // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex06?name=이순신&age=24&page=2 요청
	      // ==> 웹브라우저가 http://localhost:8080/mypro00/ex04.jsp로 요청하도록 URL이 전달됨
	  
	  		//redirect: 뒤에 url을 명시할 때, 전달할 값이 많은 경우, RedirectAttributes 를 이용할 수 있습니다.
	  
	  @GetMapping("/ex066")
	  public String myEx066(String name, Integer age, Integer page,
			                RedirectAttributes redirectAttr) {

		  System.out.println("name: " + name);
		  System.out.println("age: " + age);
		  System.out.println("page: " + page);
	      
		  redirectAttr.addAttribute("name", name);
		  redirectAttr.addAttribute("age", age);
		  redirectAttr.addAttribute("page", page);
		   
		  //[주의]아래에서 redirect: 다음에 컨텍스트이름을 포함하면 않됨
		  return "redirect:/ex04.jsp?" ;
	  }   // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex066?name=이순신&age=24&page=2 요청
	  
	  
	  @GetMapping("/ex0666")
	  public String myEx0666(String name, Integer age, Integer page,
			                RedirectAttributes redirectAttr,
			                HttpServletRequest request) {
		  
		  System.out.println("url: " + request.getRequestURI());
		  
		  System.out.println("name: " + name);
		  System.out.println("age: " + age);
		  System.out.println("page: " + page);
	      
		  redirectAttr.addAttribute("name", name);
		  redirectAttr.addAttribute("age", age);
		  redirectAttr.addAttribute("page", page);
		   
		  //[주의]아래에서 redirect: 다음에 컨텍스트이름을 포함하면 않됨
		  return "redirect:/ex04.jsp?" ;
	  }   // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex0666?name=이순신&age=24&page=2 요청
	  
	  //메서드 반환타입이 DTO, VO 인 경우 (void/String 이 아닌 경우)
	  //어떻게 JSP 파일이 호출될까요?, 반환타입이 void 일 떄와 마찬가지로 URL 정보를 기준으로 JSP 파일을 호출
	  @GetMapping("/ex07")
	  public SampleDTO myEx07(SampleDTO dto) {
		  System.out.println("dto.name: " + dto.getName());
		  System.out.println("dto.age: " + dto.getAge());
		  return dto;
	  } 
	  // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex07?name=이순신&age=24
	  // URL을 기반으로 JSP 파일을 호출합니다.
	  
	  @GetMapping("/ex077")
	  public void myEx077(SampleDTO dto) {
		  System.out.println("dto.name: " + dto.getName());
		  System.out.println("dto.age: " + dto.getAge());
	  } 
	  // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex077?name=이순신&age=24
	  
	  //@ResponseBody: REST API 어노테이션, JSP 파일 호출이 없음
	  @GetMapping("/ex08")
	  @ResponseBody
	  public SampleDTO myEx08(SampleDTO dto) {
		  System.out.println("dto.name: " + dto.getName());
		  System.out.println("dto.age: " + dto.getAge());
		  return dto;
		  
	  } 
	  // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex08?name=이순신&age=24
	  
	  //@ResponseBody: REST API 어노테이션, JSP 파일 호출이 없음
	  @GetMapping("/ex088")
	  public  @ResponseBody SampleDTO myEx088(SampleDTO dto) {
		  System.out.println("dto.name: " + dto.getName());
		  System.out.println("dto.age: " + dto.getAge());
		  return dto;
		  
	  } 
	  // 웹브라우저에서 http://localhost:8080/mypro00/sample/ex088?name=이순신&age=24
	  
	  //반환타입이 ResponseEntity<E> 인 경우, JSP 호출을 않함
	  
	  @GetMapping("/ex09")
	  public ResponseEntity<String> myEx09(SampleDTO dto){
		  System.out.println("/ex09===========================");
		  
		  HttpHeaders httpHeaders = new HttpHeaders() ;
		  httpHeaders.add("Content-Type", "text/plain; charset=utf-8");
		  					/* {"name": "홍길동", "age": 24} */
	      String myJsonStr = "{\"name\": \"" + dto.getName() + "\", \"age\": " + dto.getAge() + "}" ;
		  
//		  return new ResponseEntity<String>(myJsonStr, HttpStatus.NOT_FOUND) ;
//		  return new ResponseEntity<String>(myJsonStr, HttpStatus.OK) ;
	      return new ResponseEntity<String>(myJsonStr, httpHeaders, HttpStatus.OK);
	  } 
	  
	  
	  //웹브라우저에서 http://localhost:8080/mypro00/sample/ex09?name=홍길동&age=24
	  
	  
	  //commons-fileupload 라이브러리를 이용한 파일 업로드 처리
	  //1.Upload JSP 페이지 호출 메서드 작성(WEB-INF/views/sample/exUpload.jsp)
	  
	  @GetMapping("/exUpload") //완성된 JSP 파일이름: /WEB-INF/views/sample/exUpload.jsp
	  public void exUpload() {
		  
	  } //웹브라우저에서 http://localhost:8080/mypro00/sample/exUpload
	  
	  //톰캣 서버 상의 업로드 파일이 저장되는 경로 문자열 상수를 선언
	  private static final String uploadFolder = "C:/myupload" ; 
	  
	  @PostMapping("/exUploadPost")
	  //[주의] MultipartFile 타입의 매개변수 이름은 Form의 file타입 input의 name 속성과 동일해야 합니다.
//	  public void exUploadPost(MultipartFile myFiles, SampleDTO dto) { //전송파일이 하나일 경우
//	  public void exUploadPost(MultipartFile[] myFiles, SampleDTO dto) { //전송파일이 하나이상일 경우
	  public void exUploadPost(ArrayList<MultipartFile> myFiles, SampleDTO dto) { //전송파일이 하나이상일 경우	
		  
		  System.out.println("name: " + dto.getName());
		  System.out.println("age: " + dto.getAge());
		  
		  System.out.println("myFiles 길이: " + myFiles.size());
		  
		  //String uploadFolder = "C:/myupload" ; 
//	      //ArrayList<MultipartFile> 타입 매개변수 사용 시
		  //MultipartFile[] 타입 매개변수 사용 시 
		  for(MultipartFile yourFile : myFiles) {
			  
			  if (yourFile.getSize() > 0) {
				  System.out.println("서버에 업로도된 파일이름: " + yourFile.getOriginalFilename());
				  System.out.println("서버로 전달된 파일크기: " + yourFile.getSize());
				  
				  File saveFile = new File(uploadFolder, yourFile.getOriginalFilename()) ;
				  try {
					  yourFile.transferTo(saveFile); //전송된 파일이 서버에 저장
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  } else {
				  continue ;
			  }
			  // if-end
			  
			 
		  }// for-end
		  
		  //ArrayList<MultipartFile> 타입 매개변수 사용 시
		  
//		  myFiles.forEach(myFile -> {
//			  System.out.println("서버에 업로도된 파일이름: " + myFile.getOriginalFilename());
//			  System.out.println("서버로 전달된 파일크기: " + myFile.getSize());
//			  
//			  File saveFile = new File(uploadFolder, myFile.getOriginalFilename()) ;
//			  try {
//				myFile.transferTo(saveFile); //전송된 파일이 서버에 저장
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			  
//		  }) ;
		  
		  //업로드 테스트
	      		//업로드 페이지 표시: http://localhost:8080/mypro00/sample/exUpload 요청
	      		//  파일 선택 --> 버튼 클릭
		  
		  
	  }
	  
}     
