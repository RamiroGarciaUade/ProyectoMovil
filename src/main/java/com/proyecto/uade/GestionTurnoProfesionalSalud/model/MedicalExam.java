@Entity
public class MedicalExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String examName;
    private Integer diagnosticId;
    private Integer appointmentId;
}