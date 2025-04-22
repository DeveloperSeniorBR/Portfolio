<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty membro.id ? 'Novo Membro' : 'Editar Membro'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 600px;
            margin: 2rem auto;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        .form-title {
            color: #1e3c72;
            margin-bottom: 2rem;
        }
        .form-control:focus, .form-select:focus {
            border-color: #1e3c72;
            box-shadow: 0 0 0 0.2rem rgba(30, 60, 114, 0.25);
        }
        .btn-primary {
            background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
            border: none;
        }
        .btn-primary:hover {
            background: linear-gradient(135deg, #2a5298 0%, #1e3c72 100%);
        }
        .alert {
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <c:import url="../common/menu.jsp">
        <c:param name="active" value="membros"/>
    </c:import>

    <div class="container">
        <div class="form-container">
            <h2 class="form-title text-center">
                <i class="fas fa-users me-2"></i>
                ${empty membro.id ? 'Novo Membro' : 'Editar Membro'}
            </h2>

            <c:if test="${not empty mensagem}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${mensagem}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <c:if test="${not empty erro}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${erro}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            
            <form:form action="/membros/salvar" modelAttribute="membro" method="POST" class="needs-validation" novalidate="novalidate">
                <form:hidden path="id"/>
                
                <div class="mb-3">
                    <label for="pessoa" class="form-label">Pessoa</label>
                    <form:select path="pessoa.id" class="form-select" required="required">
                        <option value="">Selecione...</option>
                        <c:forEach items="${pessoas}" var="pessoa">
                            <option value="${pessoa.id}" ${membro.pessoa != null && membro.pessoa.id == pessoa.id ? 'selected' : ''}>${pessoa.nome}</option>
                        </c:forEach>
                    </form:select>
                    <div class="invalid-feedback">
                        Por favor, selecione uma pessoa.
                    </div>
                </div>
                
                <div class="mb-3">
                    <label for="projeto" class="form-label">Projeto</label>
                    <form:select path="projeto.id" class="form-select" required="required">
                        <option value="">Selecione...</option>
                        <c:forEach items="${projetos}" var="projeto">
                            <option value="${projeto.id}" ${membro.projeto != null && membro.projeto.id == projeto.id ? 'selected' : ''}>${projeto.nome}</option>
                        </c:forEach>
                    </form:select>
                    <div class="invalid-feedback">
                        Por favor, selecione um projeto.
                    </div>
                </div>

                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save me-2"></i>Salvar
                    </button>
                    <a href="/membros" class="btn btn-outline-secondary">
                        <i class="fas fa-arrow-left me-2"></i>Voltar
                    </a>
                </div>
            </form:form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        (function () {
            'use strict'
            var forms = document.querySelectorAll('.needs-validation')
            Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }
                        form.classList.add('was-validated')
                    }, false)
                })
        })()
    </script>
</body>
</html> 