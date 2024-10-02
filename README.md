# Sistema de Gestión de Citas y Pagos para Psicólogos
![Java](https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg)
![MySQL](https://upload.wikimedia.org/wikipedia/en/d/dd/MySQL_logo.svg)

## Descripción del Proyecto

Este proyecto está diseñado para facilitar el trabajo de un psicólogo al permitir que sus pacientes agenden citas y realicen los pagos correspondientes de manera rápida y eficiente. El sistema ofrece una interfaz intuitiva donde el psicólogo puede gestionar sus pacientes, ver citas programadas y visualizar los pagos realizados.

### Funcionalidades Clave:

- **Agendamiento de Citas**: Los pacientes pueden seleccionar la fecha y hora que deseen para sus sesiones, siempre y cuando el horario no esté ocupado.
- **Confirmación de Citas**: Una vez que se agende una cita, esta quedará registrada en el sistema, evitando duplicaciones de horarios.
- **Pagos de Sesiones**: Los pacientes pueden realizar el pago de la sesión programada a través de la plataforma. El estado del pago se reflejará en el sistema para el psicólogo.
- **Gestión de Pacientes**: El psicólogo puede ver todos los pacientes que han agendado citas, facilitando la organización y seguimiento de sus sesiones.
- **Control de Ingresos**: El psicólogo puede consultar el monto total generado a partir de las citas pagadas, lo que le permite un mejor control financiero.

## Tecnologías Utilizadas

- **Backend**: Spring Boot (Java)
- **Base de Datos**: MySQL
- **Frontend**: (Especifica aquí si estás utilizando alguna tecnología para el frontend)
- **Autenticación**: (Por ejemplo, JWT o cualquier otro método de autenticación)
- **Plataforma de Pago**: (Indica si usas Stripe, PayPal u otra plataforma de pagos)

## Instalación

Sigue estos pasos para ejecutar el proyecto localmente:

1. **Clonar el repositorio**:
   ```bash
   git clone  https://github.com/daniel-dieguez/aprendece-system.git

   ### Configuración de la Base de Datos:

1. Asegúrate de tener MySQL instalado y ejecutándose.
2. Crea una base de datos llamada `psicologo_db`.
3. Actualiza las credenciales de la base de datos en el archivo `application.properties` en el directorio `src/main/resources`.

### Ejecución del Backend:

Navega al directorio raíz del proyecto y ejecuta el siguiente comando:
