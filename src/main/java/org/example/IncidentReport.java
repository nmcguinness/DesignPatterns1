package org.example;

public class IncidentReport {

    private String _id;
    private long _timestampUtc;
    private int _durationSeconds;

    private int _severity; // 1..5
    private IncidentType _type;
    private ZoneType _zoneType;

    private String _notes;

    /// <summary>
    /// Creates a new incident report representing a single logged event in an industrial environment.
    /// </summary>
    /// <param name="id">The incident identifier (e.g., IR-001). Should not be null/blank.</param>
    /// <param name="timestampUtc">UTC timestamp (epoch seconds) indicating when the incident occurred.</param>
    /// <param name="durationSeconds">How long the incident lasted in seconds.</param>
    /// <param name="severity">Incident severity in the range 1..5.</param>
    /// <param name="type">The incident type (e.g., spill, near miss, machine fault).</param>
    /// <param name="zoneType">The zone/location where the incident occurred.</param>
    /// <param name="notes">Free-text notes for context (optional).</param>
    /// <see cref="IncidentType"/>
    /// <see cref="ZoneType"/>
    public IncidentReport(
            String id,
            long timestampUtc,
            int durationSeconds,
            int severity,
            IncidentType type,
            ZoneType zoneType,
            String notes
    ) {
        _id = id;
        _timestampUtc = timestampUtc;
        _durationSeconds = durationSeconds;
        _severity = severity;
        _type = type;
        _zoneType = zoneType;
        _notes = notes;
    }

    /// <summary>
    /// Gets the unique identifier for this incident report.
    /// </summary>
    /// <returns>The incident id.</returns>
    public String getId() {
        return _id;
    }

    /// <summary>
    /// Gets the UTC timestamp (epoch seconds) for when the incident occurred.
    /// </summary>
    /// <returns>The timestamp in epoch seconds.</returns>
    public long getTimestampUtc() {
        return _timestampUtc;
    }

    /// <summary>
    /// Gets the incident duration in seconds.
    /// </summary>
    /// <returns>The duration in seconds.</returns>
    public int getDurationSeconds() {
        return _durationSeconds;
    }

    /// <summary>
    /// Gets the severity rating for the incident (expected range: 1..5).
    /// </summary>
    /// <returns>The severity value.</returns>
    public int getSeverity() {
        return _severity;
    }

    /// <summary>
    /// Gets the incident type.
    /// </summary>
    /// <returns>The type of incident.</returns>
    /// <see cref="IncidentType"/>
    public IncidentType getType() {
        return _type;
    }

    /// <summary>
    /// Gets the zone/location where the incident occurred.
    /// </summary>
    /// <returns>The zone type.</returns>
    /// <see cref="ZoneType"/>
    public ZoneType getZoneType() {
        return _zoneType;
    }

    /// <summary>
    /// Gets any free-text notes associated with this incident.
    /// </summary>
    /// <returns>Notes providing extra context (may be null/blank).</returns>
    public String getNotes() {
        return _notes;
    }

    /// <summary>
    /// Checks whether the incident id is present and non-blank.
    /// </summary>
    /// <returns>True if the id is not null and not blank; otherwise false.</returns>
    public boolean hasValidId() {
        return _id != null && !_id.isBlank();
    }

    /// <summary>
    /// Checks whether severity is within the expected range (1..5).
    /// </summary>
    /// <returns>True if severity is between 1 and 5 inclusive; otherwise false.</returns>
    public boolean hasValidSeverity() {
        return _severity >= 1 && _severity <= 5;
    }

    /// <summary>
    /// Checks whether the duration is valid (non-negative).
    /// </summary>
    /// <returns>True if duration is greater than or equal to zero; otherwise false.</returns>
    public boolean hasValidDuration() {
        return _durationSeconds >= 0;
    }

    /// <summary>
    /// Checks whether a non-null incident type has been assigned.
    /// </summary>
    /// <returns>True if type is not null; otherwise false.</returns>
    /// <see cref="IncidentType"/>
    public boolean hasValidType() {
        return _type != null;
    }

    /// <summary>
    /// Checks whether a non-null zone type has been assigned.
    /// </summary>
    /// <returns>True if zone type is not null; otherwise false.</returns>
    /// <see cref="ZoneType"/>
    public boolean hasValidZoneType() {
        return _zoneType != null;
    }

    /// <summary>
    /// Converts this incident report to a readable string for debugging/printing.
    /// </summary>
    /// <returns>A string containing the report fields.</returns>
    @Override
    public String toString() {
        return "IncidentReport{" +
                "id='" + _id + '\'' +
                ", timestampUtc=" + _timestampUtc +
                ", durationSeconds=" + _durationSeconds +
                ", severity=" + _severity +
                ", type=" + _type +
                ", zoneType=" + _zoneType +
                ", notes='" + _notes + '\'' +
                '}';
    }
}
