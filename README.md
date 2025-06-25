@Entity
@Table(name="IAM_EMAIL_LOGS")
public class IAM_Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "IAM_EMAIL_SEQ")
    @SequenceGenerator(name = "IAM_EMAIL_SEQ", sequenceName = "IAM_EMAIL_SEQ", allocationSize = 1)
    private int RML_ID;

    @Column(name = "FRN_NO")
    private int frnno;

    @Column(name = "USER_ID")
    private int userid;

    @Column(name = "FRN_EMAIL")
    private String frnemailid;

    @Column(name = "FRN_EMAIL_DATA")
    private String emaildata;

    @Column(name = "EMAIL_DATE")
    private Date emaildate;

    @Column(name="EMAIL_STATUS")
    private String emailstatus;

    @Column(name="EMAIL_REMARK")
    private String emailremark;

}


    I also had this model class which I wanted to implment for emails logging so I can able to track down the email delivery status so guide me for implementing this model class help to write the logging just dont change existing provide code create the new method inside the class and call it from inside where code may failed or error may occuer so able to track down everthing.
    
