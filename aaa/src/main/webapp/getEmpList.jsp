<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<html>
   <head>
      <title>exam1</title>
      <style>
      	th { min-width: 130px;}
      </style>
      <script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous">
</script>
      <script>    
        var price = {'g1': 500, 'g2':100, 'g3':1000};
        
        //상품 선택시 단가 입력 및 체크 상태 변경
      	$(function(){
      		$("[name='goods']").change(function(){   
          		$(this).closest("tr").children().eq(4).text(price[$(this).val()]);
          		if($(this).val() != '') {
          			$(this).closest("tr").find("input:checkbox").attr("checked", true);
          		} else {
          			$(this).closest("tr").find("input:checkbox").attr("checked", false);
          		}
      		})
      	});  
      	
      	function makeData(){
      		let list = [];		//배열로 만들고
      		
			//체크한 행만 전송할 데이터 만들기.
      		$("[name='selId']:checked").each(function(i, checkbox){
      			var tr = $(checkbox).parent().parent();
				var td = $(tr).children();
				var obj = {};	//객체를 만듬
				
				var employee_id = td.eq(1).text();
				var ord_goods   = td.eq(3).find("select").val();
				
				//객체에 담기
				obj["customer"] = employee_id;  // 주문고객
				obj["ord_dttm"] = '';           // 신청일시
				obj["ord_goods"] = ord_goods; 	// 주문상품
				obj["ord_cnt"] = '';      		// 주문수량
				obj["price"] = '';  			// 단가
				obj["ord_price"] = '';  		// 주문금액
				
				//목록에 담기
				list.push(obj);	

      		});
			
			console.log( JSON.stringify(list) );
			
			//ajax호출
			$.ajax({
				url: "http://localhost:81/product",	//boot3으로 보낼것임
				method: "post",
				data: JSON.stringify(list),			//배열로 보낼때는 json이 편하다..
				contentType: "application/json"		//json으로 보내는 경우 contentType을 써줘야한다.
			}) .then(obj => {
				console.log("aaa")
				console.log(obj.result)
			})
      		

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
               AND commission_pct>0
             ORDER BY first_name  
         </sql:query>
	<!-- 데이터조회 끝--> 
	
      <button type="button" id="" onclick="makeData()">상품신청</button>
      <!-- 목록 시작  -->
      <table border = "1" style="width:40%">
         <tr>
         	<th><input type="checkbox" id="chkAll"></th>
            <th>사번</th>
            <th>이름</th>
            <th>상품</th>
            <th>단가</th>            
            <th>수량</th>
            <th>합계</th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}"> 
            <tr>
               <td align="center"><input type="checkbox" name="selId"></td>
               <td align="center"><c:out value = "${row.employee_id}"/></td>
               <td><c:out value = "${row.first_name} ${row.last_name}"/></td>
               <td align="center"><select name="goods"><option value="">선택</option>
                                 <option value="g1">상품1</option>
                                 <option value="g2">상품2</option>
                                 <option value="g3">상품3</option></select></td>
               <td align="right"></td>
               <td align="right"><input type="text" name="cnt"></td>
               <td></td>
            </tr>
         </c:forEach>
      </table>
      <!-- 목록 끝  -->
   </body>
</html>