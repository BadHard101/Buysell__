<!DOCTYPE html>
<html>
<head>
    <title>GruziVizi</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            padding: 1% 5% 2% 5%;
        }
    </style>
</head>
<body>
<h1 class="mt-3">GruziVizi</h1>
<hr>
<h3>Редактирование пользователя ${user.name}</h3>
<form action="/admin/user/edit" method="post">
    <div class="mb-3">
        <label class="form-label">Выберите роль:</label><br>
        <#list roles as role>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="userRole" value="${role}" id="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                <label class="form-check-label" for="${role}">${role}</label>
            </div>
        </#list>
    </div>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary">Сохранить</button>
</form>
</body>
</html>
