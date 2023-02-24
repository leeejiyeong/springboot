<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<html>
   <head>
      <title>JSTL sql:query Tag</title>
      <style>
      	th { min-width: 130px;}
      </style>
      <script src="./scripts/jquery-3.2.1.min.js"></script>
      <script>      
      	function makeData(){
      		let list = [];
      		let flag = false;	//확인용 변수 선언
      		
			   //체크한 행만 전송할 데이터 만들기. 은행계좌가 입력된 경우만 포함함.
      		$("[name='selId']:checked").each(function(i, checkbox){
      			var tr = $(checkbox).parent().parent();
               var td = $(tr).children();
               var obj = {};
               
               //+ 이름 등 변수 추가
               var employee_id = td.eq(1).text();
               var name = td.eq(2).text();
               var bankAcct    = td.eq(6).find("input").val();
               var slipAmount = Number(td.eq(3).text()) + Number(td.eq(4).text());	
               //▲ parseInt는 null값을 처리하지 못한다. 그래서 Number를 사용하는게 좋다.
               //(물론 parseInt써도 null을 0으로 바꿔서 사용할순 있긴함.)
               
               var today = new Date();
               
               //날짜 입력 방법1)
               year = today.getFullYear();
               month = today.getMonth() + 1;	//1더해져야 정상 월이 나옴 
               if(month <10 ){		//10월 보다 작으면 09월 이렇게 출력하기 위함.
            	   month = '0' + month
               }
               var salDt = '' + year + month;
               
               //날짜 입력 방법2) - 삼항연산자 사용
               //var salDt = today.getFullYear() + '' + ( (today.getMonth()+1) < 9 ? "0" + (today.getMonth())))
               
               
               //은행계좌가 없으면 skip ++
               if(bankAcct == '') {
            	  flag = true;
                  return false;		//true = continue, false = break 와 같은 기능을 한다.
               }
               
               //객체에 담기  ++ 위에서 추가한 변수를 넣어줌
               obj["slipAmount"] = slipAmount;  	     // 급여 + 상여금
               obj["salDt"] = salDt;               // 급여년월 (현재년월)
               obj["customer"] = employee_id;   // 사번_이름
               obj["bankAcct"] = bankAcct;      // 은행계좌
               
               //목록에 담기
               list.push(obj);
      		});
      		
      		//++ 계좌가 입력이 안됐을 경우 아작스 실행 안하고 뒤로 돌려보냄
      		if(flag){
      			alert("계좌입력");
      			return;
      		}
		
			   //객체를 string으로 변환
      		console.log(JSON.stringify(list));

      		//ajax 호출 ++++
      		$.ajax({
      			type: "POST",
      			url:"/exam/slip",
      			contentType: "application/json",
      			data: JSON.stringify(list)
      		}).then(map => {
      			alert(map.total + "/건")
      		});
      		

      	}
      </script>
   </head>

   <body>
	<!-- 데이터조회 시작 -->
      <sql:setDataSource var = "snapshot" driver = "oracle.jdbc.OracleDriver"
         url = "jdbc:oracle:thin:@localhost:1521:xe"
         user = "hr"  password = "hr"/>

         <sql:query dataSource = "${snapshot}" var = "result">
            SELECT e.*, round(salary*commission_pct, 2) as commission, d.department_name 
              FROM Employees e, departments d 
             WHERE e.department_id =d.department_id 
             --  AND commission_pct>0
             ORDER BY first_name  
         </sql:query>
	<!-- 데이터조회 끝--> 
	
      <button type="button" id="" onclick="makeData()">급여신청</button>
      <!-- 목록 시작  -->
      <table border = "1" width = "40%">
         <tr>
         	<th><input type="checkbox" id="chkAll"></th>
            <th>Employee_id</th>
            <th>Name</th>
            <th>Salary</th>
            <th>Commission</th>            
            <th>Deaprtment</th>
            <th>bank</th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}"> 
            <tr>
               <td align="center"><input type="checkbox" name="selId"></td>
               <td align="center"><c:out value = "${row.employee_id}"/></td>
               <td><c:out value = "${row.first_name} ${row.last_name}"/></td>
               <td align="right"><c:out value = "${row.salary}"/></td>
               <td align="right"><c:out value = "${row.commission}"/></td>
               <td align="center"><c:out value = "${row.department_name}"/></td>
               <td><input type="text" name="bank"></td>
            </tr>
         </c:forEach>
      </table>
      <!-- 목록 끝  -->
   </body>
</html>