<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty pessoa.id ? 'Nova Pessoa' : 'Editar Pessoa'}</title>
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
        .form-control:focus {
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
    <c:import url="../fragments/menu.jsp">
        <c:param name="active" value="pessoas"/>
    </c:import>

    <div class="container">
        <div class="form-container">
            <h2 class="form-title text-center">
                <i class="fas fa-user me-2"></i>
                ${empty pessoa.id ? 'Nova Pessoa' : 'Editar Pessoa'}
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
            
            <form:form action="/pessoas/salvar" modelAttribute="pessoa" method="POST" class="needs-validation" novalidate="novalidate">
                <form:hidden path="id"/>
                
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <form:input path="nome" class="form-control" required="required" />
                    <div class="invalid-feedback">
                        Por favor, informe o nome.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <form:input path="cpf" class="form-control" required="required" />
                    <div class="invalid-feedback">
                        Por favor, informe o CPF.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="dataNascimento" class="form-label">Data de Nascimento</label>
                    <input type="date" class="form-control" id="dataNascimento" name="dataNascimento" 
                           value="${pessoa.dataNascimento}" required pattern="\d{4}-\d{2}-\d{2}">
                    <div class="invalid-feedback">
                        Por favor, informe a data de nascimento.
                    </div>
                </div>

                <div class="mb-3">
                    <div class="form-check">
                        <form:checkbox path="funcionario" class="form-check-input" id="funcionario" />
                        <label class="form-check-label" for="funcionario">
                            É funcionário
                        </label>
                    </div>
                </div>

                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save me-2"></i>Salvar
                    </button>
                    <a href="/pessoas" class="btn btn-outline-secondary">
                        <i class="fas fa-arrow-left me-2"></i>Voltar
                    </a>
                </div>
            </form:form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Validação do formulário
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

        document.getElementById('dataNascimento').addEventListener('change', function(e) {
            let date = e.target.value;
            if (date.includes('/')) {
                // Converte de DD/MM/YYYY para YYYY-MM-DD
                date = date.split('/').reverse().join('-');
                e.target.value = date;
            }
        });
    </script>
</body>
</html> 