workspace "Sistema Kiosko de Beneficios y Subsidios" "Sistema para gestionar beneficios, subsidios, ciudadanos y PQRs" {

    model {
        # Personas
        ciudadano = person "Ciudadano" "Usuario que consulta sus beneficios y subsidios disponibles"
        administrador = person "Administrador" "Gestiona beneficios, subsidios, ciudadanos y PQRs"
        gestor = person "Gestor/Manager" "Revisa y gestiona las PQRs asignadas"

        # Sistema
        sistemaKiosko = softwareSystem "Sistema Kiosko" "Permite a los ciudadanos consultar beneficios y subsidios, y gestionar PQRs" {

            # Contenedores
            aplicacionWeb = container "Aplicación Web" "Interfaz de usuario para ciudadanos y administradores" "Next.js 15, React 19, TypeScript" {
                componenteConsulta = component "Componente de Consulta" "Permite consultar beneficios y subsidios por ciudadano" "React Component"
                componenteAdmin = component "Panel de Administración" "Gestiona beneficios, subsidios, ciudadanos" "React Component"
                componentePQR = component "Gestión de PQRs" "Permite crear y gestionar PQRs" "React Component"
                serviciosAPI = component "Servicios API" "Comunica con el backend" "Axios/TypeScript"
            }

            apiBackend = container "API REST Backend" "Proporciona funcionalidad de negocio vía API REST" "Spring Boot 3.5.5, Java 17" {
                # Controllers
                benefitController = component "BenefitController" "API REST para gestión de beneficios" "Spring REST Controller"
                subsidyController = component "SubsidyController" "API REST para gestión de subsidios" "Spring REST Controller"
                citizenController = component "CitizenController" "API REST para gestión de ciudadanos" "Spring REST Controller"
                pqrController = component "PQRController" "API REST para gestión de PQRs" "Spring REST Controller"
                statusController = component "GenericStatusController" "API REST para estados genéricos" "Spring REST Controller"
                managerController = component "ManagerController" "API REST para gestión de gestores" "Spring REST Controller"

                # Services
                benefitService = component "BenefitService" "Lógica de negocio de beneficios" "Spring Service"
                subsidyService = component "SubsidyService" "Lógica de negocio de subsidios" "Spring Service"
                citizenService = component "CitizenService" "Lógica de negocio de ciudadanos" "Spring Service"
                pqrService = component "PQRService" "Lógica de negocio de PQRs" "Spring Service"
                statusService = component "GenericStatusService" "Lógica de negocio de estados" "Spring Service"
                managerService = component "ManagerService" "Lógica de negocio de gestores" "Spring Service"

                # Repositories
                benefitRepo = component "BenefitRepository" "Acceso a datos de beneficios" "Spring Data JPA"
                subsidyRepo = component "SubsidyRepository" "Acceso a datos de subsidios" "Spring Data JPA"
                citizenRepo = component "CitizenRepository" "Acceso a datos de ciudadanos" "Spring Data JPA"
                pqrRepo = component "PQRRepository" "Acceso a datos de PQRs" "Spring Data JPA"
                statusRepo = component "GenericStatusRepository" "Acceso a datos de estados" "Spring Data JPA"
                managerRepo = component "ManagerRepository" "Acceso a datos de gestores" "Spring Data JPA"
                benefitPQRRepo = component "BenefitPQRRepository" "Relación Benefit-PQR" "Spring Data JPA"
            }

            baseDatos = container "Base de Datos" "Almacena información de beneficios, subsidios, ciudadanos, PQRs" "PostgreSQL 17.6" "Database"
        }

        # Relaciones de Personas con Sistema
        ciudadano -> aplicacionWeb "Consulta beneficios y subsidios"
        administrador -> aplicacionWeb "Gestiona el sistema"
        gestor -> aplicacionWeb "Gestiona PQRs asignadas"

        # Relaciones entre Contenedores
        aplicacionWeb -> apiBackend "Realiza llamadas API" "JSON/HTTPS"
        apiBackend -> baseDatos "Lee y escribe datos" "JDBC"

        # Relaciones de Componentes - Frontend
        componenteConsulta -> serviciosAPI "Usa"
        componenteAdmin -> serviciosAPI "Usa"
        componentePQR -> serviciosAPI "Usa"
        serviciosAPI -> benefitController "Llama" "JSON/HTTPS"
        serviciosAPI -> subsidyController "Llama" "JSON/HTTPS"
        serviciosAPI -> citizenController "Llama" "JSON/HTTPS"
        serviciosAPI -> pqrController "Llama" "JSON/HTTPS"
        serviciosAPI -> statusController "Llama" "JSON/HTTPS"

        # Relaciones de Componentes - Backend (Controllers -> Services)
        benefitController -> benefitService "Usa"
        subsidyController -> subsidyService "Usa"
        citizenController -> citizenService "Usa"
        pqrController -> pqrService "Usa"
        statusController -> statusService "Usa"
        managerController -> managerService "Usa"

        # Relaciones de Componentes - Backend (Services -> Repositories)
        benefitService -> benefitRepo "Usa"
        benefitService -> citizenRepo "Usa"
        benefitService -> statusRepo "Usa"
        benefitService -> benefitPQRRepo "Usa"

        subsidyService -> subsidyRepo "Usa"
        subsidyService -> citizenRepo "Usa"
        subsidyService -> statusRepo "Usa"

        citizenService -> citizenRepo "Usa"

        pqrService -> pqrRepo "Usa"
        pqrService -> managerRepo "Usa"
        pqrService -> statusRepo "Usa"

        statusService -> statusRepo "Usa"
        managerService -> managerRepo "Usa"

        # Relaciones de Componentes - Repositories -> BD
        benefitRepo -> baseDatos "Lee/Escribe" "JDBC"
        subsidyRepo -> baseDatos "Lee/Escribe" "JDBC"
        citizenRepo -> baseDatos "Lee/Escribe" "JDBC"
        pqrRepo -> baseDatos "Lee/Escribe" "JDBC"
        statusRepo -> baseDatos "Lee/Escribe" "JDBC"
        managerRepo -> baseDatos "Lee/Escribe" "JDBC"
        benefitPQRRepo -> baseDatos "Lee/Escribe" "JDBC"

        # Entornos de despliegue
        desarrollo = deploymentEnvironment "Desarrollo" {
            deploymentNode "Máquina de Desarrollo" {
                deploymentNode "Navegador Web" {
                    containerInstance aplicacionWeb
                }

                deploymentNode "Node.js Runtime" {
                    containerInstance aplicacionWeb
                }

                deploymentNode "JVM" {
                    containerInstance apiBackend
                }

                deploymentNode "Docker Container" {
                    containerInstance baseDatos
                }
            }
        }
    }

    views {
        # Vista de Contexto del Sistema
        systemContext sistemaKiosko "SystemContext" {
            include *
            autoLayout
        }

        # Vista de Contenedores
        container sistemaKiosko "Containers" {
            include *
            autoLayout
        }

        # Vista de Componentes - Frontend
        component aplicacionWeb "ComponentesFrontend" {
            include *
            autoLayout
        }

        # Vista de Componentes - Backend
        component apiBackend "ComponentesBackend" {
            include *
            autoLayout
        }

        # Vista de Componentes - Capa de Controllers
        component apiBackend "CapaControllers" {
            include ->benefitController->
            include ->subsidyController->
            include ->citizenController->
            include ->pqrController->
            include ->statusController->
            include ->managerController->
            autoLayout
        }

        # Vista de Componentes - Capa de Services
        component apiBackend "CapaServices" {
            include ->benefitService->
            include ->subsidyService->
            include ->citizenService->
            include ->pqrService->
            include ->statusService->
            include ->managerService->
            autoLayout
        }

        # Vista de Componentes - Capa de Repositories
        component apiBackend "CapaRepositories" {
            include ->benefitRepo->
            include ->subsidyRepo->
            include ->citizenRepo->
            include ->pqrRepo->
            include ->statusRepo->
            include ->managerRepo->
            include ->benefitPQRRepo->
            autoLayout
        }

        # Vista de Despliegue
        deployment * "Desarrollo" {
            include *
            autoLayout
        }

        # Temas y estilos
        styles {
            element "Person" {
                shape person
                background #08427B
                color #ffffff
            }
            element "Software System" {
                background #1168BD
                color #ffffff
            }
            element "Container" {
                background #438DD5
                color #ffffff
            }
            element "Component" {
                background #85BBF0
                color #000000
            }
            element "Database" {
                shape Cylinder
                background #438DD5
                color #ffffff
            }
        }

        theme default
    }
}