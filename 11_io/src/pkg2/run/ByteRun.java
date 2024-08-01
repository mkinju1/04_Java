package pkg2.run;

import pkg2.service.ByteService;

public class ByteRun {
	public static void main(String[] args) {
		
		ByteService service = new ByteService();
//		service.fileByteOutput();
//		service.bufferedFileByteOutput();
		
//		service.fileByteInput1();
//		service.fileByteInput2();
//		service.bufferedFileByteInput();
		
		service.fileCopypackage pkg4.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/* 문자 기반 스트림
 * 
 * - 2byte 문자(char) 단위로 입/출력하는 스트림
 * 
 * - 문자만 작성된 파일(txt), 채팅, 
 *  인터넷 요청(주소) / 응답 (html)

 * - Reader(입력),  Writer(출력) 최상위 인터페이스
 */

public class CharService {
	
	/**
	 * 문자 기반 스트림을 이용해 출력하기1
	 * - 기반 스트림만 이용
	 */
	public void fileOutput1() {
		
		// StinrgBuilder : String 불변성을 해결한 객체
		StringBuilder sb = new StringBuilder();
		
		sb.append("오늘은 8월 1일 입니다\n");
		sb.append("아쉽게도 목요일이네요...!\n");
		sb.append("아 벌써 배고파요\n");
		sb.append("abcdefg  ABCDEFG\n");
		
		String str = sb.toString();
		
		
		// 문자 기반 스트림 참조 변수 선언
		FileWriter fw = null;
		
		try {
			// 예외가 발생할 것 같은 코드를 작성하는 구문
			
			// 해당 폴더가 없으면 만들어주기
			File folder = new File("/io_test/char");
			if( !folder.exists() ) {
				folder.mkdirs();
			}
			
			// 문자 기반 스트림 객체 생성
			fw = new FileWriter("/io_test/char/문자테스트.txt");
			
			// 문자열을 지정된 파일에 출력
			// -> 자동으로 전달한 String을 한 글자씩 출력
			fw.write(str);
			
			// 스트림 밖으로 데이터를 흘려 보냄(flush)
			fw.flush();
			
			System.out.println("[출력 완료]");
			
		} catch (Exception e) {
			// try에서 던져진 예외를 잡아서 처리하는 구문
			
			e.printStackTrace();
			
		} finally {
			// try에서 예외 발생 여부 관계 없이 무조건 수행하는 구문
			
			try {
				// close() : flush() 후 스트림 닫기
				if(fw != null) fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 문자 기반 스트림을 이용해 출력하기2
	 * - 기반 + 보조 스트림
	 */
	public void fileOutput2() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("문자 기반 보조 스트림\n");
		sb.append("BufferedWriter를 이용해\n");
		sb.append("출력한 결과 입니다!!!\n");
		
		String str = sb.toString();
		
		// 스트림 참조 변수 선언
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			
		}
		
		
		
		
		
		
	}
	
	
	
	

}
();
		
		
	}
}
