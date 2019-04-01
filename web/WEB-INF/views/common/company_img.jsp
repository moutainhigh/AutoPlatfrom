<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/20
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="owl-carousel owl-carousel-carousel">
        <c:choose>
            <c:when test="${companys != null}">
                <c:forEach items="${companys}" var="c" begin="0" end="5" varStatus="s">
                    <c:choose>
                        <c:when test="${c.companyId == null}">
                            <c:choose>
                                <c:when test="${s.index == 0}">
                                    <div class="item">
                                        <a href="<%=path%>/images/img_1.jpg" class="fh5co-project-item image-popup">
                                            <img src="<%=path%>/images/img_1.jpg"
                                                 alt="Free HTML5 Bootstrap Template by ">
                                        </a>
                                    </div>
                                </c:when>
                                <c:when test="${s.index == 1}">
                                    <div class="item">
                                        <a href="<%=path%>/images/img_2.jpg" class="fh5co-project-item image-popup">
                                            <img src="<%=path%>/images/img_2.jpg"
                                                 alt="Free HTML5 Bootstrap Template by ">
                                        </a>
                                    </div>
                                </c:when>
                                <c:when test="${s.index == 2}">
                                    <div class="item">
                                        <a href="<%=path%>/images/img_3.jpg" class="fh5co-project-item image-popup">
                                            <img src="<%=path%>/images/img_3.jpg"
                                                 alt="Free HTML5 Bootstrap Template by "></a>
                                    </div>
                                </c:when>
                                <c:when test="${s.index == 3}">
                                    <div class="item">
                                        <a href="<%=path%>/images/img_7.jpg" class="fh5co-project-item image-popup">
                                            <img src="<%=path%>/images/img_7.jpg"
                                                 alt="Free HTML5 Bootstrap Template by "></a>
                                    </div>
                                </c:when>
                                <c:when test="${s.index == 4}">
                                    <div class="item">
                                        <a href="<%=path%>/images/img_5.jpg" class="fh5co-project-item image-popup">
                                            <img src="<%=path%>/images/img_5.jpg"
                                                 alt="Free HTML5 Bootstrap Template by "></a>
                                    </div>
                                </c:when>
                                <c:when test="${s.index == 5}">
                                    <div class="item">
                                        <a href="<%=path%>/images/img_6.jpg" class="fh5co-project-item image-popup">
                                            <img src="<%=path%>/images/img_6.jpg"
                                                 alt="Free HTML5 Bootstrap Template by "></a>
                                    </div>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <div class="item">
                                <a href="<%=path%>${c.companyImg}" class="fh5co-project-item image-popup">
                                    <img src="${c.companyImg}" alt="images">
                                </a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="item">
                    <a href="<%=path%>/images/img_1.jpg" class="fh5co-project-item image-popup">
                        <img src="<%=path%>/images/img_1.jpg"
                             alt="Free HTML5 Bootstrap Template by ">
                    </a>
                </div>
                <div class="item">
                    <a href="<%=path%>/images/img_2.jpg" class="fh5co-project-item image-popup">
                        <img src="<%=path%>/images/img_2.jpg"
                             alt="Free HTML5 Bootstrap Template by ">
                    </a>
                </div>
                <div class="item">
                    <a href="<%=path%>/images/img_3.jpg" class="fh5co-project-item image-popup">
                        <img src="<%=path%>/images/img_3.jpg"
                             alt="Free HTML5 Bootstrap Template by "></a>
                </div>
                <div class="item">
                    <a href="<%=path%>/images/img_7.jpg" class="fh5co-project-item image-popup">
                        <img src="<%=path%>/images/img_7.jpg"
                             alt="Free HTML5 Bootstrap Template by "></a>
                </div>
                <div class="item">
                    <a href="<%=path%>/images/img_5.jpg" class="fh5co-project-item image-popup">
                        <img src="<%=path%>/images/img_5.jpg"
                             alt="Free HTML5 Bootstrap Template by "></a>
                </div>
                <div class="item">
                    <a href="<%=path%>/images/img_6.jpg" class="fh5co-project-item image-popup">
                        <img src="<%=path%>/images/img_6.jpg"
                             alt="Free HTML5 Bootstrap Template by "></a>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>
