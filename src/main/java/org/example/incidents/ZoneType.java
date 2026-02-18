package org.example.incidents;

/// <summary>
/// Represents a named zone/location within an industrial facility where incidents can occur.
/// Used by <see cref="IncidentReport"/> to support filtering, grouping, and reporting.
/// </summary>
/// <see cref="IncidentReport"/>
public enum ZoneType {

    /// <summary>
    /// The loading/unloading area where deliveries arrive and goods are dispatched.
    /// </summary>
    LOADING_BAY,

    /// <summary>
    /// Warehouse storage area A (e.g., general inventory).
    /// </summary>
    WAREHOUSE_A,

    /// <summary>
    /// Warehouse storage area B (e.g., oversize or overflow inventory).
    /// </summary>
    WAREHOUSE_B,

    /// <summary>
    /// Production line 1 (e.g., assembly or packaging line).
    /// </summary>
    PRODUCTION_LINE_1,

    /// <summary>
    /// Production line 2 (e.g., assembly or packaging line).
    /// </summary>
    PRODUCTION_LINE_2,

    /// <summary>
    /// Chemical storage area where hazardous materials may be handled or stored.
    /// </summary>
    CHEM_STORE,

    /// <summary>
    /// Office/admin area (e.g., staff workspaces and meeting rooms).
    /// </summary>
    OFFICE
}
