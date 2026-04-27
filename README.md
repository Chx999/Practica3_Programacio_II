# Practica3_Programcio_II

Proyecto de la **Práctica 3 de Programació II** (UB).

## ✅ Estado actual (resumen rápido)
El repositorio ya tiene estructura base (`model`, `adaptador`, `vista`) y tests unitarios, pero aún faltan varias implementaciones clave.

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

### Media prioridad (consistencia entre código y tests)
- [ ] Alinear API entre implementación y tests:
  - `Exemplar`: decidir entre `getDisponible()` o `isDisponible()` y unificar.
  - `PrestecNormal` / `PrestecLlarg`: añadir constructor con `Date` en tests o adaptar tests al constructor existente.
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
2. Alinear código y tests (`isDisponible/getDisponible`, constructores de `Prestec`).
3. Ajustar/crear tests que fallen por cambios de interfaz y dejar suite en verde.

### Integración conjunta
- [ ] Hacer una sesión de merge final (30–45 min).
- [ ] Ejecutar todos los tests antes de entregar.
- [ ] Revisar README y estructura final del proyecto.

---

## 🗓️ Propuesta rápida de ejecución
- **Día 1:** Chris termina `Llista` + base de `Dades`; tt termina `Adaptador` base.
- **Día 2:** Resolver inconsistencias con tests y reglas de préstamo.
- **Día 3:** Integración, pruebas finales y entrega.

---

Si queréis, en el siguiente commit os preparo también una sección de **"Comandos para ejecutar"** (compilación + tests) para dejar el README totalmente listo para entrega.
