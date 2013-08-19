package com.p000.util;
public class HangulChosungSearch {
    private static final char INITIAL_SOUND_BEGIN_UNICODE = 12593; // 초성 유니코드 시작 값
    private static final char INITIAL_SOUND_LAST_UNICODE = 12622;  // 초성 유니코드 마지막 값
    private static final char HANGUL_BEGIN_UNICODE = 44032;        // 한글 유니코드 시작 값
    private static final char HANGUL_LAST_UNICODE = 55203;         // 한글 유니코드 마지막 값
    private static final char NUMBER_BEGIN_UNICODE = 48;           // 숫자 유니코드 시작 값
    private static final char NUMBER_LAST_UNICODE = 57;            // 숫자 유니코드 마지막 값
    private static final char ENGLISH_ROWER_BEGIN_UNICODE = 65;    // 영문(소문자) 유니코드 시작 값 
    private static final char ENGLISH_ROWER_LAST_UNICODE = 90;     // 영문(소문자) 유니코드 마지막 값
    private static final char ENGLISH_UPPER_BEGIN_UNICODE = 97;    // 영문(대문자) 유니코드 시작 값
    private static final char ENGLISH_UPPER_LAST_UNICODE = 122;    // 영문(대문자) 유니코드 마지막 값
    private static final char HANGUL_BASE_UNIT = 588;              // 자음 마다 가지는 글자수
                                                                   // 초성
    private static final char[] INITIAL_SOUND = 
        {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ'
       , 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};
 
    // 초성 검색에 대한 상품 리스트 
    public static final String[][] ITEM_ARRAY = new String[][]{
      //  상품번호 , 도서명 , 도서가격 , 판매건수
         {"8798725", "샐러드를 좋아하는 사자, 사슴?", "11700", "5"}
       , {"8787608", "아날로그 사랑법", "12600", "32"}
       , {"8809046", "엄마가 있어줄게", "12420", "72"}
       , {"8762353", "하고 싶다, 연애", "12600", "12"}
       , {"8822780", "버스 타고 제주여행 제주 구석구석 즐기는 법", "12600", "5"}
       , {"8824942", "대한민국 사진여행지 100", "15120", "21"}
       , {"8885635", "베스트 오브 유럽  230", "11900", "22"}
       , {"8930742", "지면서 이기는 관계술", "13500", "6"}
       , {"8885924", "공부와 열정", "11700", "9"}
       , {"7845549", "소셜 웹 마이닝", "21600", "121"}
       , {"243767", "학문의 즐거움", "7120", "851"}
       };
     
    /**
     * 입력받은 문자가 초성인지 체크
     */
    private static boolean isInitialSound(char c){
        for(int i=0; i<INITIAL_SOUND.length; i++){ 
            if(INITIAL_SOUND[i] == c){ 
                return true; 
            } 
        } 
        return false; 
    } 
 
    /**
     * 입력받은 문자의 자음을 추출
     */
    private static char getInitialSound(char c){ 
        int hanBegin = (c - HANGUL_BEGIN_UNICODE); 
        int index = hanBegin / HANGUL_BASE_UNIT; 
        return INITIAL_SOUND[index]; 
    } 
     
    /**
     * 입력받은 문자가 한글인지 체크
     */
    private static boolean isHangul(char c){ 
        return HANGUL_BEGIN_UNICODE <= c && c <= HANGUL_LAST_UNICODE; 
    }
     
    /**
    * 숫자, 영문(대소문자), 한글, 초성 등의 유니코드를 체크
    */
    private static boolean checkUnicode(char c){
        if ((   (c >= NUMBER_BEGIN_UNICODE && c <= NUMBER_LAST_UNICODE) 
             || (c >= ENGLISH_UPPER_BEGIN_UNICODE && c <= ENGLISH_UPPER_LAST_UNICODE) 
             || (c >= ENGLISH_ROWER_BEGIN_UNICODE && c <= ENGLISH_ROWER_LAST_UNICODE) 
             || (c >= HANGUL_BEGIN_UNICODE && c <= HANGUL_LAST_UNICODE) 
             || (c >= INITIAL_SOUND_BEGIN_UNICODE && c <= INITIAL_SOUND_LAST_UNICODE))
           ){
            return true;
        }else{
            return false;
        }
    }
 
    /**
    * 검색어와 검색 대상 값을 입력받아서 일치여부 확인
    */
    public static boolean matchString(String keyword, String value){ 
        int t = 0; 
        int seof = value.length() - keyword.length(); 
        int slen = keyword.length();
        // 검색어가 검색대상값보다 더 길거나, 검색어가 두개 이하일때 false 리턴 
        if(seof < 0 || keyword.length() < 2){
            return false;
        }
         
        for(int i = 0;i <= seof;i++){ 
            t = 0; 
            while(t < slen){ 
                // 숫자, 초성, 한글, 영문(대소문자)를 제외한 다른 값은 false 리턴 
                if(!checkUnicode(keyword.charAt(t))){
                    return false;
                }
                 
                // 검색어가 초성이이고 한글이면 초성끼리 비교
                if(isInitialSound(keyword.charAt(t)) && isHangul(value.charAt(i+t))){ 
                    // 각각의 초성이 같은지 비교 
                    if(getInitialSound(value.charAt(i+t)) == keyword.charAt(t)){ 
                        t++; 
                    }else{ 
                        break;
                    }
                } else { 
                    // 초성이 아닐경우 비교 
                    if(value.charAt(i+t) == keyword.charAt(t)){
                        t++; 
                    }else{ 
                        break;
                    }
                } 
            } 
            // 검색어의 길이만큼 모두 일치하면 true를 리턴 
            if(t == slen){
                return true;
            }
        } 
        return false; // 일치하지 않으면 false를 리턴
    }
     
    /**
    * 상품명 검색및 매출액에 따른 정렬
    */
    public static void searchItem(String keyword){
        String[] itemTitle = new String[20]; // 상품명을 담을 변수
        int[] machool = new int[20];         // 매출금액을 담을 변수
         
        // 총 상품의 건수만큼 for 문을 수행 
        for(int i=0; i<ITEM_ARRAY.length; i++){
            // 키워드와 매칭되는 상품이 존재하면, 상품명과 매출액을 배열에 각각 저장 
            if(matchString(keyword, ITEM_ARRAY[i][1])){
                itemTitle[i] = ITEM_ARRAY[i][1];
                machool[i] = Integer.parseInt(ITEM_ARRAY[i][2])*Integer.parseInt(ITEM_ARRAY[i][3]);
            }
        }
         
        // 매출액이 큰 순서부터 내림차순 정렬 
        for (int i = 0; i < machool.length; i++){
            boolean changed = false;
 
            for (int j = i + 1; j < machool.length; j++){
                if (machool[j] > machool[i]){
                    // 매출액의 순서를 정렬 
                    int tempMachool = machool[j];
                    machool[j] = machool[i];
                    machool[i] = tempMachool;
                    // 상품명의 순서를 정렬
                    String tempItemTitle = itemTitle[j];
                    itemTitle[j] = itemTitle[i];
                    itemTitle[i] = tempItemTitle;
                    changed = true;
                }
            } // 정렬 for문 종료
            if (!changed){ // 더이상 순서 변경이 없으면 for문을 빠져 나옴
                break;
            }
            System.out.println("도서명 :"+itemTitle[i]+", 매출액 :"+machool[i]);
        } // 전체 for문 종료
    }
 
    // 메인 메서드 
    public static void main(String[] arr){
        // 검색할 초성 입력
        searchItem("줄게");
    }
}
/*
### 출력 결과 ###
도서명 :학문의 즐거움, 매출액 :6059120
도서명 :엄마가 있어줄게, 매출액 :894240
도서명 :버스 타고 제주여행 제주 구석구석 즐기는 법, 매출액 :63000
*/