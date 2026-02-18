package org.example.incidents;

/// <summary>
/// The type/category of an industrial incident recorded in an <see cref="IncidentReport"/>.
/// </summary>
/// <see cref="IncidentReport"/>
public enum IncidentType {

    /// <summary>
    /// A chemical/oil/water spill that requires cleanup and may create a slip hazard.
    /// </summary>
    SPILL,

    /// <summary>
    /// An event that could have caused harm/damage but did not, often used for safety learning.
    /// </summary>
    NEAR_MISS,

    /// <summary>
    /// An unauthorized entry attempt or security-related event.
    /// </summary>
    INTRUSION,

    /// <summary>
    /// A machine malfunction that interrupts production (e.g., jam, overheating, sensor failure).
    /// </summary>
    MACHINE_FAULT,

    /// <summary>
    /// A fire alarm activation (may be genuine or false alarm).
    /// </summary>
    FIRE_ALARM,

    /// <summary>
    /// A planned evacuation drill for training/compliance purposes.
    /// </summary>
    EVACUATION_DRILL,

    /// <summary>
    /// A workplace injury event requiring first aid/medical attention and reporting.
    /// </summary>
    INJURY,

    /// <summary>
    /// Loss of power affecting operations (partial or full outage).
    /// </summary>
    POWER_OUTAGE
}
