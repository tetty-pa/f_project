<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <th scope="row">Login</th>
                        <td> ${sessionScope.user.login}</td>
                    </tr>
                    <tr>
                        <th scope="row">Name</th>
                        <td> ${sessionScope.user.name}</td>
                    </tr>
                    <tr>
                        <th scope="row">Surname</th>
                        <td> ${sessionScope.user.surname}</td>
                    </tr>
                    <c:if test="${sessionScope.user.urlDocument==null}">
                        <tr>
                            <th>Upload Documents :</th>
                            <td>
                                <form id="comm1" method="POST" action="${pageContext.request.contextPath}/controller/"
                                      enctype="multipart/form-data">
                                    <input type="hidden" name="command" value="uploadDocuments"/>
                                    <input type="file" name="file" size="50"/>
                                    <br>
                                    <input class="btn btn-secondary" type="submit" value="Upload File"/>

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
                            <td>Surname :</td>
                            <td><input type="text" class="form-control" name="surname" value="${sessionScope.user.surname}"></td>
                        </tr>
                        <tr>
                            <td>Name :</td>
                            <td><input type="text" class="form-control" name="name" value="${sessionScope.user.name}"></td>
                        </tr>
                        <tr>
                            <td>Password :</td>
                            <td><input type="password" class="form-control" name="password" value="${sessionScope.user.password}">
                            </td>
                        </tr>
                        <tr>
                            <td>Login :</td>
                            <td><input type="email" class="form-control" name="login" value="${sessionScope.user.login}"></td>
                        </tr>

                    </table>

                    <div class="container">
                        <button type="submit" class="btn btn-outline-primary">Save</button>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button id="edit-profile-button" type="button" class="btn btn-primary">Edit</button>
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