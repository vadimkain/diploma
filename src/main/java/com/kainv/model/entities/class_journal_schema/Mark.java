    package com.kainv.model.entities.class_journal_schema;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    @Table(name = "marks", schema = "class_journal_schema")
    public class Mark {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name", unique = true, nullable = false, length = 25)
        private String name;

        @Column(name = "min", nullable = false)
        private Short min;

        @Column(name = "max", nullable = false)
        private Short max;

        @Column(name = "is_differentiated", nullable = false)
        private Boolean isDifferentiated;

        // getters and setters
    }
