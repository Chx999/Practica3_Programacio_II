# Practica3_Programcio_II

Proyecto de la **Práctica 3 de Programació II** (UB).

## ✅ Estado actual (actualizado)
El proyecto mantiene la estructura base (`model`, `adaptador`, `vista`) y batería de tests unitarios.

### Avances recientes
- [x] **Exemplar** unificado con `isDisponible()`.
- [x] **Préstamos** actualizados para usar constructor con fecha:
  - `Prestec(Exemplar, Usuari, Date)`
  - `PrestecNormal(Exemplar, Usuari, Date)`
  - `PrestecLlarg(Exemplar, Usuari, Date)`
- [x] README reorganizado con checklist y reparto de trabajo.

---

## 📌 Checklist de tareas pendientes

### Alta prioridad (bloquean funcionamiento)
- [ ] Implementar métodos pendientes en `Llista.java`:
  - `getSize`, `afegir`, `esborrar`, `getAt`, `clear`, `isEmpty`.
- [ ] Implementar lógica de negocio en `Dades.java`:
  - `afegirExemplar`, `afegirUsuari`, `afegirPrestec`, `retornarPrestec`, `recuperaPrestecsNoRetornats`.
- [ ] Implementar métodos pendientes en `Adaptador.java`:
  - altas de ejemplares/usuarios/préstamos,
  - devolución,
  - guardado y carga de datos.

### Media prioridad (consistencia y validaciones)
- [x] Alinear API entre implementación y tests (disponibilidad de `Exemplar` + constructores de `Prestec`).
- [ ] Revisar reglas de préstamos:
  - límites por tipo de usuario,
  - disponibilidad del ejemplar,
  - validación de préstamo largo.

### Baja prioridad (entrega y calidad)
- [ ] Añadir/actualizar documentación mínima de uso (cómo ejecutar app y tests).
- [ ] Revisar mensajes de excepción (`BiblioException`) para que sean claros.
- [ ] Limpieza final de estilo y nombres antes de entrega.

---

## 👥 Reparto de trabajo (Chris + tt)

### Chris (Backend lógico)
1. Implementar completamente `Llista.java`.
2. Implementar reglas de negocio en `Dades.java`.
3. Validar con tests de `LlistaTest` y `DadesTest`.

### tt (Integración + calidad)
1. Implementar `Adaptador.java` (incluyendo persistencia: guardar/cargar).
2. Verificar integración entre `Adaptador` y `Dades`.
3. Ajustar/crear tests que fallen por cambios de interfaz y dejar suite en verde.

### Integración conjunta
- [ ] Hacer una sesión de merge final (30–45 min).
- [ ] Ejecutar todos los tests antes de entregar.
- [ ] Revisar README y estructura final del proyecto.

---

## 🗓️ Próximos pasos sugeridos
- **Paso 1:** cerrar `Llista.java`.
- **Paso 2:** cerrar `Dades.java` con reglas y excepciones.
- **Paso 3:** conectar `Adaptador.java` + persistencia.
- **Paso 4:** correr tests completos y preparar entrega.
