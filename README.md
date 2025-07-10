UCN,	Firm Registration No.*,	Firm Name*,	PAN NO.*,	GSTIN NO,	Address Line,	City,	State Name,	District Name,	Pin Code*,	Primary Mobile No*,	Point of Contact,	Point of Contact's Designation,	Point of Contact Email,	Type of Establishment

This are the fields inside the excel and * mentioned fields and mandotory fields means not null 

This is the entitty class which has exact same fields as per excel

@Entity
@Table(name = "IAM_FIRM_MASTER_DETAILS")
@Getter
@Setter
public class Firm_Master {

    @Column(name = "BRANCH_CODE")
    private int branchcode;

    @Column(name = "BRNACH_NAME")
    private String branchname;

    @Column(name = "UCN_NO")
    private int ucnno;

    @Id
    @Column(name = "FRN_NO")
    private String frnno;

    @Column(name = "FIRM_NAME")
    private String firmname;

    @Column(name = "PAN_NO")
    private String panno;

    @Column(name = "GSTN_NO")
    private String gstnno;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "PIN_CODE")
    private String pincode;

    @Column(name = "MOB_NO")
    private String mobno;

    @Column(name = "CONTACT_PERSON")
    private String contactperson;

    @Column(name = "POC_EMAIL")
    private String pocEmail;

    @Column(name = "FIRM_TYPE")
    private String firmtype;

    @Column(name = "POC_DESIGNATION")
    private String pocDesignation;


}
