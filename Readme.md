# Estructura del Proyecto: Supermercado

```text
Supermercado/
│
├── pom.xml
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           │
│       │           ├── modelo/                   # Entidades de datos puras
│       │           │   ├── clientes/
│       │           │   │   ├── Cliente.java
│       │           │   │   └── ClienteVIP.java
│       │           │   ├── empleados/
│       │           │   │   ├── Empleado.java
│       │           │   │   ├── Cajero.java
│       │           │   │   └── Gerente.java
│       │           │   └── productos/
│       │           │       ├── Producto.java
│       │           │       ├── ProductoAlimenticio.java
│       │           │       ├── ProductoElectronico.java
│       │           │       ├── ProductoHigiene.java
│       │           │       └── TipoDeUso.java
│       │           │
│       │           ├── servicio/                 # Lógica del negocio y gestión de datos
│       │           │   └── SupermercadoService.java   (tu actual Supermercado.java)
│       │           │
│       │           ├── vista/                    # Formularios gráficos (Swing / NetBeans GUI)
│       │           │   ├── FrmPrincipal.java     (Ventana principal con menú/botones de acceso)
│       │           │   ├── FrmProducto.java      (Ventana para registrar/listar productos)
│       │           │   ├── FrmCliente.java       (Ventana para registrar clientes)
│       │           │   ├── FrmVenta.java         (Ventana para realizar compras y ver stock)
│       │           │   └── FrmEmpleado.java      (Ventana para salarios y empleados)
│       │           │
│       │           ├── controlador/              # Intermediarios entre Vista y Servicio
│       │           │   ├── CtrlPrincipal.java
│       │           │   ├── CtrlProducto.java
│       │           │   ├── CtrlCliente.java
│       │           │   ├── CtrlVenta.java
│       │           │   └── CtrlEmpleado.java
│       │           │
│       │           ├── interfaces/               # Contratos de diseño
│       │           │   ├── ImpuestoAplicable.java
│       │           │   └── Promocionable.java
│       │           │
│       │           ├── excepciones/              # Manejo de errores de negocio
│       │           │   ├── ClienteNoRegistradoException.java
│       │           │   └── ProductoNoDisponibleException.java
│       │           │
│       │           └── Main.java                 # Punto de entrada que arranca Swing
│       │
│       └── resources/                            # Archivos estáticos
│           └── imagenes/                         # Íconos (add.png, delete.png, etc.)
```