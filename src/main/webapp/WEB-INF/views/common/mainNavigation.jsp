<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <div class="container">
        <a class="navbar-brand" href="/">
            <i class="fas fa-project-diagram me-2"></i>Portfolio Manager
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link ${param.active == 'pessoas' ? 'active' : ''}" href="/pessoas">
                        <i class="fas fa-users me-1"></i>Pessoas
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${param.active == 'projetos' ? 'active' : ''}" href="/projetos">
                        <i class="fas fa-project-diagram me-1"></i>Projetos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${param.active == 'membros' ? 'active' : ''}" href="/membros">
                        <i class="fas fa-user-friends me-1"></i>Membros
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav> 