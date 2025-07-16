@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iam_req_seq")
@SequenceGenerator(name = "iam_req_seq", sequenceName = "YOUR_SEQUENCE", allocationSize = 1)
@Column(name = "REQUEST_ID", updatable = false, insertable = false)
private int requestid;