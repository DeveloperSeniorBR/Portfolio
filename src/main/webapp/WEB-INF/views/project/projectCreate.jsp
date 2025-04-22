<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty projeto.id ? 'Novo Projeto' : 'Editar Projeto'}</title>
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
    <c:import url="../common/menu.jsp">
        <c:param name="active" value="projetos"/>
    </c:import>

    <div class="container">
        <div class="form-container">
            <h2 class="form-title text-center">
                <i class="fas fa-project-diagram me-2"></i>
                ${empty projeto.id ? 'Novo Projeto' : 'Editar Projeto'}
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
            
            <form:form action="/projetos/salvar" modelAttribute="projeto" method="POST" class="needs-validation" novalidate="novalidate">
                <form:hidden path="id"/>
                
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <form:input path="nome" class="form-control" required="required" />
                    <div class="invalid-feedback">
                        Por favor, informe o nome do projeto.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="dataInicio" class="form-label">Data de Início</label>
                    <form:input path="dataInicio" type="date" class="form-control" required="required" />
                    <div class="invalid-feedback">
                        Por favor, informe a data de início.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="dataPrevisaoFim" class="form-label">Previsão de Término</label>
                    <form:input path="dataPrevisaoFim" type="date" class="form-control" required="required" />
                    <div class="invalid-feedback">
                        Por favor, informe a previsão de término.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="dataFim" class="form-label">Data de Término</label>
                    <form:input path="dataFim" type="date" class="form-control" />
                </div>

                <div class="mb-3">
                    <label for="descricao" class="form-label">Descrição</label>
                    <form:textarea path="descricao" class="form-control" rows="3" />
                </div>

                <div class="mb-3">
                    <label for="status" class="form-label">Status</label>
                    <form:select path="status" class="form-control" required="required">
                        <form:option value="EM_ANALISE">Em Análise</form:option>
                        <form:option value="ANALISE_REALIZADA">Análise Realizada</form:option>
                        <form:option value="ANALISE_APROVADA">Análise Aprovada</form:option>
                        <form:option value="INICIADO">Iniciado</form:option>
                        <form:option value="PLANEJADO">Planejado</form:option>
                        <form:option value="EM_ANDAMENTO">Em Andamento</form:option>
                        <form:option value="ENCERRADO">Encerrado</form:option>
                        <form:option value="CANCELADO">Cancelado</form:option>
                    </form:select>
                    <div class="invalid-feedback">
                        Por favor, selecione o status.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="orcamento" class="form-label">Orçamento</label>
                    <form:input path="orcamento" type="number" step="0.01" class="form-control" required="required"/>
                    <div class="invalid-feedback">
                        Por favor, informe o orçamento.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="risco" class="form-label">Risco</label>
                    <form:select path="risco" class="form-control" required="required">
                        <form:option value="BAIXO">Baixo</form:option>
                        <form:option value="MEDIO">Médio</form:option>
                        <form:option value="ALTO">Alto</form:option>
                    </form:select>
                    <div class="invalid-feedback">
                        Por favor, selecione o nível de risco.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="gerente" class="form-label">Gerente</label>
                    <form:select path="gerente.id" class="form-select" required="required">
                        <option value="">Selecione...</option>
                        <c:forEach items="${pessoas}" var="pessoa">
                            <option value="${pessoa.id}" ${projeto.gerente != null && projeto.gerente.id == pessoa.id ? 'selected' : ''}>${pessoa.nome}</option>
                        </c:forEach>
                    </form:select>
                    <div class="invalid-feedback">
                        Por favor, selecione o gerente do projeto.
                    </div>
                </div>

                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save me-2"></i>Salvar
                    </button>
                    <a href="/projetos" class="btn btn-outline-secondary">
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