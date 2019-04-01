<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/20
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <c:choose>
        <c:when test="${companys != null}">
            <c:forEach items="${companys}" var="c" begin="0" end="5" varStatus="s">
                <c:choose>
                    <c:when test="${c.companyId == null}">
                        <c:choose>
                            <c:when test="${s.index == 0}">
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <a href="<%=path%>/images/img_1.jpg" class="fh5co-project-item image-popup">
                                        <figure>
                                            <div class="overlay"><i class="ti-plus"></i></div>
                                            <img src="<%=path%>/images/img_1.jpg" alt="Image"
                                                 class="img-responsive">
                                        </figure>
                                        <div class="fh5co-text">
                                            <h2>华晨汽修</h2>
                                            <p>江西省赣州市汽车精品一条街1G栋120号</p>
                                        </div>
                                    </a>
                                </div>
                            </c:when>
                            <c:when test="${s.index == 1}">
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <a href="<%=path%>/images/img_2.jpg" class="fh5co-project-item image-popup">
                                        <figure>
                                            <div class="overlay"><i class="ti-plus"></i></div>
                                            <img src="<%=path%>/images/img_2.jpg" alt="Image"
                                                 class="img-responsive">
                                        </figure>
                                        <div class="fh5co-text">
                                            <h2>中国汽修</h2>
                                            <p>江西省赣州市汽车精品一条街1G栋120号</p>
                                        </div>
                                    </a>
                                </div>
                            </c:when>
                            <c:when test="${s.index == 2}">
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <a href="<%=path%>/images/img_3.jpg" class="fh5co-project-item image-popup">
                                        <figure>
                                            <div class="overlay"><i class="ti-plus"></i></div>
                                            <img src="<%=path%>/images/img_3.jpg" alt="Image"
                                                 class="img-responsive">
                                        </figure>
                                        <div class="fh5co-text">
                                            <h2>牛掰了汽修</h2>
                                            <p>江西省赣州市汽车精品一条街1G栋120号</p>
                                        </div>
                                    </a>
                                </div>
                            </c:when>
                            <c:when test="${s.index == 3}">
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <a href="<%=path%>/images/img_5.jpg" class="fh5co-project-item image-popup">
                                        <figure>
                                            <div class="overlay"><i class="ti-plus"></i></div>
                                            <img src="<%=path%>/images/img_5.jpg" alt="Image"
                                                 class="img-responsive">
                                        </figure>
                                        <div class="fh5co-text">
                                            <h2>嘻嘻汽修</h2>
                                            <p>江西省赣州市汽车精品一条街1G栋120号</p>
                                        </div>
                                    </a>
                                </div>
                            </c:when>
                            <c:when test="${s.index == 4}">
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <a href="<%=path%>/images/img_7.jpg" class="fh5co-project-item image-popup">
                                        <figure>
                                            <div class="overlay"><i class="ti-plus"></i></div>
                                            <img src="<%=path%>/images/img_7.jpg" alt="Image"
                                                 class="img-responsive">
                                        </figure>
                                        <div class="fh5co-text">
                                            <h2>宝宝汽修</h2>
                                            <p>江西省赣州市汽车精品一条街1G栋120号</p>
                                        </div>
                                    </a>
                                </div>
                            </c:when>
                            <c:when test="${s.index == 5}">
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <a href="<%=path%>/images/img_9.jpg" class="fh5co-project-item image-popup">
                                        <figure>
                                            <div class="overlay"><i class="ti-plus"></i></div>
                                            <img src="<%=path%>/images/img_9.jpg" alt="Image"
                                                 class="img-responsive">
                                        </figure>
                                        <div class="fh5co-text">
                                            <h2>其他汽修</h2>
                                            <p>江西省赣州市汽车精品一条街1G栋120号</p>
                                        </div>
                                    </a>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <div class="col-lg-4 col-md-4 col-sm-6">
                            <a href="${c.companyImg}" class="fh5co-project-item image-popup">
                                <figure>
                                    <div class="overlay"><i class="ti-plus"></i></div>
                                    <img src="${c.companyImg}" alt="Image" class="img-responsive">
                                </figure>
                                <div class="fh5co-text">
                                    <h2>${c.companyName}</h2>
                                    <p>${c.companyAddress}</p>
                                </div>
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <a href="<%=path%>/images/img_1.jpg" class="fh5co-project-item image-popup">
                    <figure>
                        <div class="overlay"><i class="ti-plus"></i></div>
                        <img src="<%=path%>/images/img_1.jpg" alt="Image"
                             class="img-responsive">
                    </figure>
                    <div class="fh5co-text">
                        <h2>华晨汽修</h2>
                        <p>江西省赣州市汽车精品一条街1G栋120号</p>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <a href="<%=path%>/images/img_2.jpg" class="fh5co-project-item image-popup">
                    <figure>
                        <div class="overlay"><i class="ti-plus"></i></div>
                        <img src="<%=path%>/images/img_2.jpg" alt="Image"
                             class="img-responsive">
                    </figure>
                    <div class="fh5co-text">
                        <h2>中国汽修</h2>
                        <p>江西省赣州市汽车精品一条街1G栋120号</p>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <a href="<%=path%>/images/img_3.jpg" class="fh5co-project-item image-popup">
                    <figure>
                        <div class="overlay"><i class="ti-plus"></i></div>
                        <img src="<%=path%>/images/img_3.jpg" alt="Image"
                             class="img-responsive">
                    </figure>
                    <div class="fh5co-text">
                        <h2>牛掰了汽修</h2>
                        <p>江西省赣州市汽车精品一条街1G栋120号</p>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <a href="<%=path%>/images/img_5.jpg" class="fh5co-project-item image-popup">
                    <figure>
                        <div class="overlay"><i class="ti-plus"></i></div>
                        <img src="<%=path%>/images/img_5.jpg" alt="Image"
                             class="img-responsive">
                    </figure>
                    <div class="fh5co-text">
                        <h2>嘻嘻汽修</h2>
                        <p>江西省赣州市汽车精品一条街1G栋120号</p>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <a href="<%=path%>/images/img_7.jpg" class="fh5co-project-item image-popup">
                    <figure>
                        <div class="overlay"><i class="ti-plus"></i></div>
                        <img src="<%=path%>/images/img_7.jpg" alt="Image"
                             class="img-responsive">
                    </figure>
                    <div class="fh5co-text">
                        <h2>宝宝汽修</h2>
                        <p>江西省赣州市汽车精品一条街1G栋120号</p>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <a href="<%=path%>/images/img_9.jpg" class="fh5co-project-item image-popup">
                    <figure>
                        <div class="overlay"><i class="ti-plus"></i></div>
                        <img src="<%=path%>/images/img_9.jpg" alt="Image"
                             class="img-responsive">
                    </figure>
                    <div class="fh5co-text">
                        <h2>其他汽修</h2>
                        <p>江西省赣州市汽车精品一条街1G栋120号</p>
                    </div>
                </a>
            </div>
        </c:otherwise>
    </c:choose>


</div>