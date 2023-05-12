/**
 * 
 */
/*
** 약관 동의 여부 확인 및 회원가입 화면 요청
*/
function go_next() {
	if(document.formm.okon1[0].checked == true) {	// '동의함'이 선택된 경우
		// 회원가입 화면 요청
		document.formm.action = "join_form";
		document.formm.submit();
	} else if(document.formm.okon1[1].checked == true){
		// 경고창 표시
		alert("약관에 동의하셔야 합니다.")
	}
}

/*
 * 사용자 ID 중복체크 화면 표시
 */
function idcheck() {
	// ID 값 입력 여부 확인
	if (document.getElementById("id").value == ""){ // HTML 문서에서 id의 요소 값을 이용하여 찾아가기(공백이라면)
		alert("아이디를 입력해 주세요.");					// 입력 요청 경고창 출력
		document.getElementById("id").focus();		// 사용자의 편의성을 위해 해당 id값의 위치로 자동 포커싱
		
		return false;	// 재입력 요청
	} 
	
	// 아이디가 입력된 경우 작은 팝업 윈도우 표시
	var url = "id_check_form?id=" + document.getElementById("id").value;
	window.open(url, "_blank_", "toolbar=no, menubar=no," +
			" scrollbars=yes, resizable=no, width=450, height=350")	
}

/*
 * 회원가입 시 필수입력 확인
 */
function go_save() {
	if (document.getElementById("id").value == "") {
		alert("사용자 아이디를 입력해 주세요.");
		document.getElementById("id").focus();
		
		return false;
	} else if (document.getElementById("reid").value == "") {
		alert("아이디 중복체크를 해주세요.");
		document.getElementById("id").focus();
	
		return false;
	} else if (document.getElementById("pwd").value == "") {
		alert("비밀번호를 입력해주세요.");
		document.getElementById("pwd").focus();
		
		return false;
	} else if (document.getElementById("pwd").value !=
		document.getElementById("pwdCheck").value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.getElementById("pwd").focus();
		
		return false;
	} else if (document.getElementById("name").value == "") {
		alert("이름을 입력해주세요.");
		document.getElementById("name").focus();
		
		return false;
	} else if (document.getElementById("email").value == ""){
		alert("이메일을 입력해주세요.");
		document.getElementById("email").focus();
		
		return false;
	} else {
		document.getElementById("join").action = "join"; // 회원가입 요청 URL
		document.getElementById("join").submit();
	}
}

/*
 * 우편번호 찾기 화면 요청
 */
function post_zip() {
	var url = "find_zip_num";
	window.open(url, "_blank_", "toolbar=no, menubar=no," +
			" scrollbars=yes, resizable=no, width=700, height=500")	
}

