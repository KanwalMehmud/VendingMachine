<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">       
        <title>Vending Machine</title>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align:center">Vending Machine</h1><hr>
            <div class="container">
                <ul class="list-group" id="errorMessages"></ul>
                <!-- vending items -->
                <div class="row">
                    <div class="col-md-8" id="vendingItems">
                        <c:forEach var="vendingItems" items="${items}">           
                            <div class="col-md-4">
                                <div class="panel panel-default">
                                    <a href="selectedItem?id=${vendingItems.id}">
                                        <div class="panel-body" style="text-align:center" >
                                            <p style="text-align:left" >${vendingItems.id}</p>
                                            <p> ${vendingItems.itemName}</p>
                                            <p>${vendingItems.itemCost}</p>
                                            <p> Quantity left: ${vendingItems.inventoryCount}</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>





                    <!-- form -->
                    <div class="col-md-4" id="forms">
                        <h2> Total $ In</h2>
                        <form>
                            <div class="form-group" id="totalAmount" style="border-style:solid; height:30px"><c:out value="${totalMoney}"/></div>

                            <div class="form-group">
                                <div class="row">
                                    <div class="col-md-6"><a href="totalMoneyIn?amount=1"><button type="button" id="addDollarButton" class="btn btn default" >Add Dollar</button></a></div>
                                    <div class="col-md-6"><a href="totalMoneyIn?amount=0.25"><button type="button" id="addQuarterButton" class="btn btn default">Add Quarter</button></a></div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-6"><a href="totalMoneyIn?amount=0.10"> <button type="button" id="addDimeButton" class="btn btn default">Add Dime</button></a></div>
                                    <div class="col-md-6"> <a href="totalMoneyIn?amount=0.05"><button type="button" id="addNickelButton" class="btn btn default">Add Nickel</button></a></div>
                                </div>
                            </div>
                        </form>
                        <hr>

                        <div class="row">
                            <h2>Messages</h2>
                            <form>
                                <div class="form-group" style="border-style:solid; height:30px">
                                    <c:out value="${message}"/>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-2"> <label for="item"> Item: </label> </div>
                                        <div id="displayId" class="col-md-6" style="border-style:solid; height:30px">


                                            <c:out value="${itemId}"/>

                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-3"></div>
                                        <div class="col-md-3"><a href="makePurchase"><button type="button" id="makePurchaseButton" class="btn btn default">Make Purchase</button></a></div>
                                    </div>
                                </div>
                            </form>
                            <hr>
                        </div>
                        <div class="row">
                            <h2>Change</h2>
                            <form>
                                <div class="form-group" id="change" style="border-style: solid; height:40px"> 
                                    <c:if test="${change.changeInQuarters>0}">
                                        Quarters  <c:out value="${change.changeInQuarters}"/> 
                                    </c:if>
                                    <c:if test="${change.changeInDimes>0}">
                                       Dimes  ${change.changeInDimes} 
                                    </c:if>
                                    <c:if test="${change.changeInNickels>0}">
                                        Nickels  ${change.changeInNickels} 
                                    </c:if>
                                    <c:if test="${change.changeInPennies>0}">
                                        Pennies ${change.changeInPennies}
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-3"></div>
                                    <div class="col-md-3">
                                        <a href="returnChange">
                                            <button type="button" id="changeReturnButton" class="btn btn default">Change Return
                                            </button>
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
