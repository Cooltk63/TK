@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iam_req_seq")
@SequenceGenerator(name = "iam_req_seq", sequenceName = "IAM_REQUEST_ID_SEQ", allocationSize = 1)
@Column(name = "REQUEST_ID", insertable = false, updatable = false)
private int requestid;