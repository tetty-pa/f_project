<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<!-- Modal -->
<div class="modal fade" id="profile-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header ui-icon-background">
                <h5 class="title" id="exampleModalLabel">My profile</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="table" id="profile-details">
                    <tr>
                        <th scope="row"><fmt:message key="header.login"/></th>
                        <td> ${sessionScope.user.login}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="label.name"/></th>
                        <td> ${sessionScope.user.name}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="label.surname"/></th>
                        <td> ${sessionScope.user.surname}</td>
                    </tr>
                    <c:if test="${sessionScope.user.urlDocument==null}">
                        <tr>
                            <th><fmt:message key="button.upload_file"/> :</th>
                            <td>
                                <form id="comm1" method="POST" action="${pageContext.request.contextPath}/controller/"
                                      enctype="multipart/form-data">
                                    <input type="hidden" name="command" value="uploadDocuments"/>
                                    <input type="file" name="file" size="50"/>
                                    <br>
                                    <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.upload_file"/>"/>

                                </form>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
            <div id="profile-edit" style="display: none;">
                <h3 class="mt-2">Please Edit Carefully</h3>
                <form action="${pageContext.request.contextPath}/controller/" method="post">
                    <input type="hidden" name = "command" value="editProfile">
                    <table class="table">
                        <tr>
                            <td><fmt:message key="label.surname"/> :</td>
                            <td><input type="text" class="form-control" name="surname" value="${sessionScope.user.surname}"/>
                        </tr>
                        <tr>
                            <td><fmt:message key="label.name"/> :</td>
                            <td><input type="text" class="form-control" name="name" value="${sessionScope.user.name}"/>
                        </tr>
                        <tr>
                            <td><fmt:message key="label.password"/> :</td>
                            <td><input type="password" class="form-control" name="password" value="${sessionScope.user.password}"/>
                        </tr>
                        <tr>
                            <td><fmt:message key="newPassword..confirm_new_password"/> :</td>
                            <td><input type="password" class="form-control" name="passwordSecond" value=""/>
                        </tr>
                       <%-- <tr>
                            <td><fmt:message key="login.title"/> :</td>
                            <input type="email" class="form-control" name="login" value="${sessionScope.user.login}"/>
                        </tr>--%>

                    </table>

                    <div class="container">
                        <button type="submit" class="btn btn-outline-primary"><fmt:message key="button.submit"/></button>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="button.close"/></button>
                <button id="edit-profile-button" type="button" class="btn btn-primary"><fmt:message key="button.edit"/></button>
            </div>
        </div>
    </div>
</div>


<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>

<script>
    $(document).ready(function () {
        let editStatus = false;
        $('#edit-profile-button').click(function () {
            if (editStatus === false) {
                $("#profile-details").hide()
                $("#profile-edit").show();
                editStatus = true;
                $(this).text("Back")
            } else {
                $("#profile-details").show()
                $("#profile-edit").hide();
                editStatus = false;
                $(this).text("Edit")
            }
        })
    });
</script>