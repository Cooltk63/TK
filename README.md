import React, {useEffect, useState} from "react"
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import {DialogTitle, FormControl, FormLabel, Grid, InputAdornment, MenuItem, Radio, RadioGroup,} from "@mui/material";
import FormControlLabel from "@mui/material/FormControlLabel";
import {Box} from "@mui/system";
import TextField from "@mui/material/TextField";
import IconButton from "@mui/material/IconButton";
import CancelIcon from "@mui/icons-material/Cancel";
import HighlightOffIcon from "@mui/icons-material/HighlightOff";
import AddIcon from "@mui/icons-material/Add";
import propTypes from "prop-types";
import {Close} from "@mui/icons-material";
import Divider from "@mui/material/Divider";
import axios from "axios";
import {validateEmail, validations} from "../CommonValidations/Validations";
import EditIcon from "@mui/icons-material/Edit";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import {SnackbarProvider} from "notistack";
import SearchIcon from "@mui/icons-material/Search";
import CircularProgress from "@mui/material/CircularProgress";


const CreateUser = (props) => {
    const [openCreateUser, setOpenCreateUser] = useState(false);
    const [role, setRole] = useState('');
    const [pfNumber, setPfNumber] = useState('');
    const [pfNumberDisabled, setPfNumberDisabled] = useState(true);
    const [branchCode, setBranchCode] = useState('');
    const [roCode, setROCode] = useState('');
    const [firstName, setFirstName] = useState('');
    const [middleName, setMiddleName] = useState('');
    const [lastName, setLastName] = useState('');
    const [mobileNumber, setMobileNumber] = useState('');
    const [emailId, setEmailId] = useState('');
    const [messageForPf, setMessageForPf] = useState(null);
    const [messageForBranchCode, setMessageForBranchCode] = useState(null);
    const [messageFoRoCode, setMessageFoRoCode] = useState(null);
    const [messageForFirstName, setMessageForFirstName] = useState(null);
    const [messageForMiddleName, setMessageForMiddleName] = useState(null);
    const [messageForLastName, setMessageForLastName] = useState(null);
    const [messageForMobileNumber, setMessageForMobileNumber] = useState(null);
    const [messageForEmail, setMessageForEmail] = useState(null);
    const [title, setTitle] = useState('Create New User');
    const [disableItem, setDisableItem] = useState(false);
    const [showEdit, setShowEdit] = useState(false)
    const [circle_code, setCircle_Code] = useState('')
    const [data, setData] = useState([]);
    const [roData, setRoData] = useState([]);
    const [selectedItem, setSelectedItem] = useState('');
    const [showTextField, setShowTextField] = useState(false);
    const [snackbar, setSnackbar] = React.useState(null);


    const handleCloseSnackbar = () => setSnackbar(null);

    const iv = crypto.getRandomValues(new Uint8Array(12)); /* // for encryption*/
    const ivBase64 = btoa(String.fromCharCode.apply(null, iv)); /*// for be decryption*/
    const salt = crypto.getRandomValues(new Uint8Array(16));  /*// for encryption*/
    const saltBase64 = btoa(String.fromCharCode.apply(null, salt)); /*// for be decryption*/

    /*lokesh*/
    const [open, setOpen] = React.useState(false);
    const [branchCodeValid, setBranchCodeValid] = useState(false);

    const handleDialogClose = () => {
        setOpen(false);
    };

    /**
     * @author: V1014064
     * function to ensure the user entered branch code lies under the specific branch list under the respective circle */
    const branchCodeValidation = (e) => {
        console.log('inside handle branch code ');
        console.log('inside handle branch list :: '+JSON.stringify(data));

        for(let i=0;i<data.length;i++){
            console.log('branch '+ i +' :: '+data[i].split(',')[0]);
            if(e.target.value === data[i].split(',')[0]) {
                setBranchCode(e.target.value);
                setBranchCodeValid('T')
                setMessageForBranchCode(null);
                break;
            }
            else {
                setBranchCode('');
                setBranchCodeValid('N')
                setMessageForBranchCode("Invalid Branch");
                console.log(branchCode + ` is not present the array`);
            }
        }
        handleDialogClose();
    }

    /*lokesh*/


    const handleHrmsClick = async () => {
        if (pfNumber.length === 0) {
            console.log("CLICK in search butten then",pfNumber);
            setMessageForPf('Enter Pf to fetch data from HRMS')
            try {
                const response = await axios.post('Server/userModule/searchUserByPf',
                    {
                        pfNumber: pfNumber
                    }, {
                        headers: {
                            'Authorization': `Bearer ${localStorage.getItem('token')}`,
                        }
                    });
                setBranchCode(response.data.result.BRANCH_CODE);
                setFirstName(JSON.stringify(response.data.result.FIRST_NAME));
                setMiddleName(response.data.result.MIDDLE_NAME);
                setLastName(response.data.result.LAST_NAME);
                setMobileNumber(response.data.result.MOBILE_NUMBER);
                setEmailId(response.data.result.EMAIL);
            } catch (e) {
                console.error(e);
                console.log("error...." + e);
            }
        } else {
            try {
                const response = await axios.post('Server/userModule/getHrms',
                    {
                        pfNumber: pfNumber
                    }, {
                        headers: {
                            'Authorization': `Bearer ${localStorage.getItem('token')}`,
                        }
                    });
                console.log(response);
                setBranchCode(response.data.result.BRANCH_CODE);
                setFirstName(response.data.result.FIRST_NAME);
                setMiddleName(response.data.result.MIDDLE_NAME);
                setLastName(response.data.result.LAST_NAME);
                setMobileNumber(response.data.result.MOBILE_NUMBER);
                setEmailId(response.data.result.EMAIL);
            } catch (e) {
                console.error(e);
            }
        }
        try {
            const response = await axios.post('Server/userModule/getBranchCodeList',
                {
                    circle_code: circle_code
                }, {
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                })
            setData(response)
        } catch (e) {
            console.error(e);
        }
    }

    const handleRoCodeClick = async () => {
        console.log('handleRoCodeClick ::::')
        try {
            const response = await axios.post('Server/userModule/getRoCodeList',
                {
                    circle_code: circle_code
                }, {
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                })
            setRoData((response.data.result))
        } catch (e) {
            console.error(e);
        }
    }

    const handleRadio = (e) => {
        setRole(e.target.value);
        if (e.target.value === 'RO Manager') {
            //setShowTextField(true);
            handleRoCodeClick();
        }
        if (e.target.value === 'Maker' || 'Checker' || 'Auditor' || 'LHO Manager' || 'RO Manager') {
            setShowTextField(true);
        } else {
            setShowTextField(false);
        }
        if (e.target.value === "Auditor") {
            setMessageForEmail('Only @sbi.co.in domain not accepted');
        } else {
            setMessageForEmail('Only @sbi.co.in domain accepted');
        }
    }

    const handleEdit = () => {
        setTitle('Edit User, ' + props.userDetails.FIRST_NAME);
        setDisableItem(false);
        setShowEdit(false);
    }

    const handleSave = async () => {
        try {
            if (showTextField === false) {
                setSnackbar({children: 'Please select the role first', severity: 'error'});
                return;
            } else if (pfNumber.toString().length !== 7) {
                setSnackbar({children: 'PF Number must contain 7 digits', severity: 'error'});
                return;
            }
            if (role === '' || pfNumber === '' ||/* branchCode === '' ||*/ firstName === '' || lastName === '' || emailId === '' || mobileNumber === '') {
                setSnackbar({children: 'All fields are mandatory', severity: 'error'});
                return;
            } else if (role || pfNumber || branchCode || firstName || lastName || emailId || mobileNumber) {
                setSnackbar({children: 'Data submitted successfully', severiy: 'success'});
                handleClose();
            }
            const userRoleMap = {
                'Maker': '1',
                'Checker': '9',
                'Auditor': '11',
                'RO Manager': '75',
                'LHO Manager': '50',
            }
            const userRoleId = userRoleMap[role];

            const response = await axios.post('/Server/userModule/saveUser', {
                    user_role: userRoleId,
                    pf_number: pfNumber.toString(),
                    branch_code: branchCode,
                    first_name: firstName,
                    middle_name: middleName,
                    last_name: lastName,
                    mobile_number: mobileNumber,
                    email_id: emailId,
                    region_code: roCode
                },
                {
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('token')}`,
                    }
                });
            console.log(response);
            console.log("save button called for data storing :" + response.data);
            props.handleFab();

            if (response.data === true) {
                setSnackbar({children: 'data save successfully', severity: 'success'});
                handleClose();

            } else {
                setSnackbar({children: 'data not saved', severity: 'error'})
            }
            /*setSnackbar({children: 'data save successfully', severity: 'success'});*/

            setTimeout(() => {
                setRole(' ');
                setBranchCode('');
                setROCode('');
                setPfNumber('');
                setFirstName('');
                setLastName('');
                setMiddleName('');
                setEmailId('');
                setMobileNumber('');
                setSelectedItem('');

            }, 3000);

        } catch (e) {
            if (e.response && e.response.status === 400) {
                console.log('bad request, user creations failed.');
                setSnackbar({children: 'user creations failed', severity: 'error'});
            } else {
                console.log('An error occurred', e.message);
                setSnackbar({children: 'An error occurred. Please try again.', severity: 'error'});
            }
        }
    }

    const extractedClearData = () => {
        setBranchCode('');
        setROCode('');
        setPfNumber('');
        setFirstName('');
        setLastName('');
        setMiddleName('');
        setEmailId('');
        setMobileNumber('');
        setRole('');
        setSelectedItem('');
        setMessageForPf(null);
        setMessageFoRoCode(null);
        setMessageForFirstName(null);
        setMessageForMiddleName(null);
        setMessageForLastName(null);
        setMessageForMobileNumber(null);
        setMessageForEmail(null);
        //.setMessageForBranchCode(null);
    }

    const handleDiscard = () => {
        extractedClearData();

    }

    const handleClose = () => {
        extractedClearData()
        props.handleFab();
        setShowTextField(false);
    };


    function setUser() {
        setBranchCode(props.userDetails.BRANCH_CODE);
        setPfNumber(props.userDetails.PF_NUMBER);
        setFirstName(props.userDetails.FIRST_NAME);
        setLastName(props.userDetails.LAST_NAME);
        setMiddleName(props.userDetails.MIDDLE_NAME);
        setEmailId(props.userDetails.EMAIL_ID);
        setMobileNumber(props.userDetails.MOBILE_NUMBER);
        setRole(props.userDetails.USER_ROLE)
    }

    const handleSelect = (e) => {
        setSelectedItem(e.target.value);
    }

    useEffect(() => {
        console.log(props.btnClick)

        if (props.btnClick === 'view') {
            setShowEdit(true);
            setDisableItem(true);
            setTitle('User Details of: ' + props.userDetails.FIRST_NAME);
            setShowTextField(true);
            setPfNumberDisabled(true);
            setUser();
        } else if (props.btnClick === 'edit') {
            setShowEdit(true);
            setDisableItem(true);
            setShowTextField(true);
            setTitle('Edit User, ' + props.userDetails.FIRST_NAME);
            setPfNumberDisabled(true);
            setUser();
        } else {
            setDisableItem(false);
            setShowEdit(false);
            setTitle('Create New User');
            setShowTextField(false);
            setPfNumberDisabled(false);
            extractedClearData();
        }
    }, [props.openCreateUser]);


    const FieldValidation = (type, value, inputField) => {
        let result = validations(type, value, inputField);

        if (inputField === 'Email ID') {
            if (validateEmail('emailIdInput', value, role)) {
                setEmailId(value);
                setMessageForEmail(null);
            } else {
                setMessageForEmail('Please enter a valid email id');
            }
        }
        if (inputField === 'PF Number') {
            if (result === '') {
                setPfNumber(value);
                setMessageForPf(null);
            } else {
                setMessageForPf(result);
            }
        }
        /**
         * @author: V1014064
         * setting branch code value as the user inputs the data*/

        if(inputField === 'branchCode') {
            if(result === '') {
                setBranchCode(value);
            }
            else{
                setBranchCodeValid('N')
                setMessageForBranchCode(result);
            }
        }

        if (inputField === 'RO Code') {
            if (result === '') {
                setROCode(value);
                setMessageFoRoCode(null);
            } else {
                setMessageFoRoCode(result);
            }
        }
        if (inputField === 'First Name') {
            if (result === '') {
                setFirstName(value);
                setMessageForFirstName(null);
            } else {
                setMessageForFirstName(result);
            }
        }
        if (inputField === 'Middle Name') {
            if (result === '') {
                setMiddleName(value);
                setMessageForMiddleName(null);
            } else {
                setMessageForMiddleName(result);
            }
        }
        if (inputField === 'Last Name') {
            if (result === '') {
                setLastName(value);
                setMessageForLastName(null);
            } else {
                setMessageForLastName(result);
            }
        }
        if (inputField === 'Mobile Number') {
            if (result === '') {
                setMobileNumber(value);
                setMessageForMobileNumber(null);
            } else {
                setMessageForMobileNumber(result);
            }
        }
    }
    return (<>
            <Dialog maxWidth="lg"
                    PaperProps={{
                        style: {
                            height: "650px",
                        },
                    }}
                    open={props.openCreateUser}
                    onClose={() => setOpenCreateUser(false)}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description">
                <DialogTitle sx={{m: 0, p: 2}} id="customized-dialog-title">
                    User Details
                </DialogTitle>
                <IconButton
                    aria-label="close"
                    onClick={handleClose}
                    sx={{
                        position: 'absolute',
                        right: 8,
                        top: 8,
                        color: (theme) => theme.palette.grey[500],
                    }}
                >
                    <Close/>
                </IconButton>
                <Divider/>

                <DialogContent>
                    <FormControl>
                        <Grid container spacing={4}>
                            <Grid item xs={1}>
                                <FormLabel component="legend" sx={{mt: 1, ml: 1}}>Role</FormLabel>
                            </Grid>
                            <Grid item>
                                <RadioGroup
                                    row
                                    aria-label="role"
                                    name="role"
                                    value={role}
                                    defaultValue="Maker"
                                    onChange={handleRadio}
                                >
                                    <FormControlLabel
                                        disabled={disableItem}
                                        value="Maker"
                                        control={<Radio/>}
                                        label="Maker"
                                        sx={{
                                            '& .MuiFormControlLabel-label': {
                                                marginLeft: '10px'
                                            }
                                        }}
                                    />
                                </RadioGroup>
                            </Grid>

                            <Grid item>
                                <RadioGroup
                                    row
                                    aria-label="role"
                                    name="role"
                                    value={role}
                                    onChange={handleRadio}
                                >
                                    <FormControlLabel
                                        disabled={disableItem}
                                        value="Checker"
                                        control={<Radio/>}
                                        label="Checker"
                                        sx={{
                                            '& .MuiFormControlLabel-label': {
                                                marginLeft: '10px'
                                            }
                                        }}
                                    />
                                </RadioGroup>
                            </Grid>
                            <Grid item>
                                <RadioGroup
                                    row
                                    aria-label="role"
                                    name="role"
                                    value={role}
                                    onChange={handleRadio}
                                >
                                    <FormControlLabel
                                        disabled={disableItem}
                                        value="Auditor"
                                        control={<Radio/>}
                                        label="Auditor"
                                        sx={{
                                            '& .MuiFormControlLabel-label': {
                                                marginLeft: '10px'
                                            }
                                        }}/>
                                </RadioGroup>
                            </Grid>

                            <Grid item>
                                <RadioGroup
                                    row
                                    aria-label="role"
                                    name="role"
                                    value={role}
                                    onChange={handleRadio}
                                >
                                    <FormControlLabel
                                        disabled={disableItem}
                                        value="RO Manager"
                                        control={<Radio/>}
                                        label="RO Manager"
                                        sx={{
                                            '& .MuiFormControlLabel-label': {
                                                marginLeft: '10px'
                                            }
                                        }}
                                    />
                                </RadioGroup>
                            </Grid>

                            <Grid item>
                                <RadioGroup
                                    row
                                    aria-label="role"
                                    name="role"
                                    value={role}
                                    onChange={handleRadio}
                                >
                                    <FormControlLabel
                                        disabled={disableItem}
                                        value="LHO Manager"
                                        control={<Radio/>}
                                        label="LHO Manager"
                                        sx={{
                                            '& .MuiFormControlLabel-label': {
                                                marginLeft: '10px'
                                            }
                                        }}
                                    />
                                </RadioGroup>
                            </Grid>
                        </Grid>
                    </FormControl>
                    {showTextField &&
                        <Box
                            component="form"
                            sx={{
                                '& .MuiTextField-root': {m: 1, width: '25ch'},
                            }}>
                            <div style={{alignItems: 'center'}}>
                                <TextField
                                    inputProps={{maxLength: 7}}
                                    error={messageForPf !== null}
                                    disabled={disableItem || pfNumberDisabled}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    disabled={disableItem || pfNumberDisabled}
                                                    onClick={() => {
                                                        setPfNumber('');
                                                        setMessageForPf(null);
                                                    }}>
                                                    <CancelIcon/>
                                                </IconButton>
                                            </InputAdornment>
                                        ),
                                    }}
                                    label="PF Number"
                                    required
                                    fullWidth
                                    value={pfNumber}
                                    onBlur={() => {
                                        if (pfNumber === '') {
                                            setMessageForPf(null);
                                        }
                                    }}
                                    onChange={(e) => FieldValidation('numInput', e.target.value, 'PF Number')}
                                    helperText={pfNumber.length < 8 ? 'Only digit max:7.' : ""}
                                    InputLabelProps={{
                                        sx: {".MuiInputLabel-asterisk": {color: "red"}}
                                    }}
                                    margin="normal"
                                />
                                &emsp;
                                <Button variant="contained" disableElevation
                                        disabled={disableItem}
                                        startIcon={<SearchIcon/>}
                                        sx={role === 'Auditor' ? {display: 'none', mt: 2} : {mt: 2}}
                                        onClick={() => {
                                            handleHrmsClick();
                                        }}>
                                    Search
                                </Button>
                            </div>

                            <div>
                                {/*<TextField
                                    error={messageForBranchCode !== null}
                                    label="BranchCode"
                                    select
                                    value={selectedItem}
                                    onBlur={() => {
                                        if (branchCode === '') {
                                            setMessageForBranchCode(null);
                                        }
                                    }}
                                    onChange={handleSelect}
                                    variant="outlined"
                                    disabled={disableItem}
                                    SelectProps={{
                                        MenuProps: {
                                            paperProps: {
                                                sx: {
                                                    maxHeight: '150',
                                                },
                                            },
                                            getContentAnchorEl: null,
                                            anchorOrigin: {
                                                vertical: 'bottom',
                                                horizontal: 'left'
                                            },
                                            transformOrigin: {
                                                vertical: 'top',
                                                horizontal: 'left'
                                            },
                                            PopperProps: {
                                                disablePortal: true,
                                            },
                                        },
                                    }}>
                                    {data.map((item, index) => (
                                        <MenuItem
                                            key={index} value={item.split(',')[0]}>{item.split('-')[1]}
                                        </MenuItem>
                                    ))}
                                </TextField>*/}
                                {/**@author: V1014064 text field for the branch code*/}
                                <TextField
                                    //disabled={!isVisible}
                                    disabled={disableItem}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    disabled={disableItem}
                                                    onClick={() => {
                                                        setBranchCode(' ');
                                                        setMessageForBranchCode(null);
                                                    }}>
                                                    <CancelIcon/>
                                                </IconButton>
                                            </InputAdornment>
                                        )
                                    }}
                                    label="BranchCode"
                                    required
                                    fullWidth
                                    value={branchCode}
                                    inputProps={{maxLength:5}}
                                    error={messageForBranchCode !== null}
                                    helperText={(branchCodeValid === 'N') ? messageForBranchCode : ""}
                                    onChange={(e) => FieldValidation('numInput', e.target.value, 'branchCode')}
                                    onBlur={(e) => {setOpen(true);branchCodeValidation(e);}}
                                    margin="normal"
                                />

                                <TextField
                                    error={messageFoRoCode !== null}
                                    label="Ro Code"
                                    select
                                    value={selectedItem}
                                    onBlur={() => {
                                        if (roCode === '') {
                                            setMessageFoRoCode(null);
                                        }
                                    }}
                                    onChange={handleSelect}
                                    variant="outlined"
                                    /* disabled={disableItem}*/
                                    disabled={disableItem}
                                    SelectProps={{
                                        MenuProps: {
                                            paperProps: {
                                                sx: {
                                                    maxHeight: '10',
                                                },
                                            },
                                            getContentAnchorEl: null,
                                            anchorOrigin: {
                                                vertical: 'bottom',
                                                horizontal: 'left'
                                            },
                                            transformOrigin: {
                                                vertical: 'top',
                                                horizontal: 'left'
                                            },
                                            PopperProps: {
                                                disablePortal: true,
                                            },
                                        },
                                        InputProps: {
                                            endAdornment: (
                                                <InputAdornment position="end">
                                                    <IconButton
                                                        disabled={disableItem}

                                                        onClick={() => {
                                                            setROCode('');
                                                            setMessageFoRoCode(null);
                                                        }}>
                                                        <CancelIcon/>
                                                    </IconButton>
                                                </InputAdornment>)
                                        }
                                    }}
                                    sx={(role === 'Checker' || role === 'Maker' || role === 'Auditor' || role === 'LHO Manager') ? {
                                        display: 'none',
                                        mt: 2
                                    } : {mt: 2}}
                                    required
                                    fullWidth
                                    inputProps={{maxLength: 9, minLength: 9}}
                                    helperText={roCode.length < 9 ? 'Only digit max:9.' : ""}
                                    InputLabelProps={{
                                        sx: {".MuiInputLabel-asterisk": {color: "red"}}
                                    }}
                                    margin="normal"
                                >
                                    {roData.map((item, index) => (
                                        <MenuItem sx={{maxHeight: 20}}
                                                  key={index} value={roData[index]}>{roData[index]}
                                        </MenuItem>
                                    ))}
                                </TextField>

                            </div>
                            <div>
                                <TextField
                                    error={messageForFirstName !== null}
                                    disabled={disableItem}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    disabled={disableItem}
                                                    onClick={() => {
                                                        setFirstName('');
                                                        setMessageForFirstName(null);

                                                    }}>
                                                    <CancelIcon/>
                                                </IconButton>
                                            </InputAdornment>
                                        )
                                    }}
                                    // disabled={disableItem}
                                    id="outlined-multiline-flexible"
                                    multiline
                                    maxRows={4}
                                    inputProps={{maxLength: 40}}
                                    label="First Name"
                                    required
                                    value={firstName}
                                    onBlur={() => {
                                        if (firstName === '') {
                                            setMessageForFirstName(null);
                                        }
                                    }}
                                    onChange={(e) => FieldValidation('inputValue', e.target.value, "First Name")}
                                    helperText={(firstName.length < 1 || firstName.length > 40) ? 'Only Alphabets max:40.' : ""}
                                    InputLabelProps={{
                                        sx: {".MuiInputLabel-asterisk": {color: "red"}}
                                    }}
                                    margin="normal"
                                />

                                <TextField
                                    disabled={disableItem}
                                    error={messageForMiddleName !== null}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    disabled={disableItem}
                                                    onClick={() => {
                                                        setMiddleName('');
                                                        setMessageForMiddleName(null);
                                                    }}>
                                                    <CancelIcon/>
                                                </IconButton>

                                            </InputAdornment>
                                        )
                                    }}
                                    id="outlined-multiline-flexible"
                                    multiline
                                    maxRows={4}
                                    label="Middle Name"
                                    value={middleName}
                                    inputProps={{maxLength: 40, minLength: 1}}
                                    onBlur={() => {
                                        if (middleName === '') {
                                            setMessageForMiddleName(null);
                                        }
                                    }}
                                    onChange={(e) => FieldValidation('inputValue', e.target.value, "Middle Name")}
                                    helperText={(middleName.length < 1 || middleName.length > 40) ? 'Only Alphabets max:40.' : ""}
                                    margin="normal"
                                />
                                <TextField
                                    error={messageForLastName !== null}
                                    disabled={disableItem}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    disabled={disableItem}
                                                    onClick={() => {
                                                        setLastName('');
                                                        setMessageForLastName(null);

                                                    }}>
                                                    <CancelIcon/>
                                                </IconButton>
                                            </InputAdornment>
                                        )
                                    }}
                                    id="outlined-multiline-flexible"
                                    multiline
                                    maxRows={4}
                                    label="Last Name"
                                    required
                                    value={lastName}
                                    inputProps={{maxLength: 40}}
                                    onBlur={() => {
                                        if (lastName === '') {
                                            setMessageForLastName(null);
                                        }
                                    }}
                                    onChange={(e) => FieldValidation('inputValue', e.target.value, "Last Name")}
                                    helperText={(lastName.length < 1 || lastName.length > 40) ? 'Only Alphabets max:40.' : ""}
                                    InputLabelProps={{
                                        sx: {".MuiInputLabel-asterisk": {color: "red"}}
                                    }}
                                    margin="normal"
                                />

                            </div>

                            <div>
                                <TextField
                                    error={messageForMobileNumber !== null}
                                    disabled={disableItem}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    disabled={disableItem}
                                                    onClick={() => {
                                                        setMobileNumber('');
                                                        setMessageForMobileNumber(null);
                                                    }}>
                                                    <CancelIcon/>
                                                </IconButton>
                                            </InputAdornment>)
                                    }}
                                    label="Mobile Number"
                                    required
                                    fullWidth
                                    inputProps={{
                                        pattern: '[0-9]{10}',
                                        maxLength: 10,
                                        minLength: 10,
                                    }}
                                    value={mobileNumber}
                                    onChange={(e) => FieldValidation('numInput', e.target.value, "Mobile Number")}
                                    onBlur={() => {
                                        if (mobileNumber === '') {
                                            setMessageForMobileNumber(null);
                                        }
                                    }}
                                    helperText={(mobileNumber.length < 10) ? 'Only digit max:10.' : ""}
                                    InputLabelProps={{
                                        sx: {".MuiInputLabel-asterisk": {color: "red"}}
                                    }}
                                    margin="normal"
                                />

                            </div>
                            <div>
                                {/*    ..............email id..............*/}
                                <TextField
                                    error={messageForEmail !== ('Only @sbi.co.in domain accepted') && messageForEmail !== ('Only @sbi.co.in domain not accepted') && messageForEmail !== null}
                                    disabled={disableItem}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    disabled={disableItem}
                                                    onClick={() => {
                                                        setEmailId('');
                                                        setMessageForEmail(null);
                                                    }}>
                                                    <CancelIcon/>
                                                </IconButton>
                                            </InputAdornment>)
                                    }}
                                    label="Email ID"
                                    required
                                    fullWidth
                                    value={emailId}
                                    helperText={messageForEmail !== ('Only @sbi.co.in domain accepted') && messageForEmail !== ('Only @sbi.co.in domain not accepted')
                                    && messageForEmail !== null ? messageForEmail : (role === 'Auditor' ? 'Only @sbi.co.in domain not accepted' : 'Only @sbi.co.in domain accepted')}
                                    onChange={(event) => setEmailId(event.target.value)}
                                    onBlur={(e) =>
                                        FieldValidation('emailIdInput', e.target.value, 'Email ID')
                                    }
                                    InputLabelProps={{
                                        sx: {".MuiInputLabel-asterisk": {color: "red"}}
                                    }}
                                    margin="normal"
                                />
                            </div>
                        </Box>
                    }
                </DialogContent>
                <Divider/>
                <DialogActions sx={{
                    justifyContent: 'center'
                }}>
                    <Button
                        style={{margin: 10}}
                        /*disabled={disableItem}*/
                        sx={disableItem ? {display: 'none'} : {}}
                        variant="contained"
                        startIcon={<HighlightOffIcon/>}
                        onClick={() => {
                            handleClose();
                            handleDiscard();
                        }}
                    > Discard</Button>

                    <Button

                        sx={disableItem ? {display: 'none'} : {}}
                        variant="contained"
                        startIcon={<AddIcon/>}
                        onClick={() => {
                            handleSave();
                            // handleSubmit();
                            //handleClose();

                        }}
                    >Save
                    </Button>


                    <Button
                        variant="contained"
                        startIcon={<EditIcon/>}
                        sx={showEdit ? {mr: 1} : {display: 'none', mr: 1}}
                        onClick={handleEdit}
                    >Edit
                    </Button>

                    {/*lokesh branchcode changes*/}
                    <Dialog
                        PaperProps={{
                            style: {
                                backgroundColor: 'transparent',
                                boxShadow: 'none',
                            },
                        }}
                        open={open}
                        aria-labelledby="alert-dialog-title"
                        aria-describedby="alert-dialog-description"
                    >
                        <Box>
                            <DialogContent sx={{display: 'Grid'}}>
                                <CircularProgress />
                            </DialogContent>
                        </Box>
                    </Dialog>
                    {/*lokesh branch code changes*/}
                </DialogActions>
            </Dialog>
            {!!snackbar && (
                <SnackbarProvider maxSnack={3}>
                    <Snackbar
                        open
                        anchorOrigin={{vertical: 'bottom', horizontal: 'right'}}
                        onClose={handleCloseSnackbar}
                        autoHideDuration={5000}

                    >
                        <Alert
                            variant="filled"
                            {...snackbar} onClose={handleCloseSnackbar}/>
                    </Snackbar>
                </SnackbarProvider>
            )}
        </>

    )
}

CreateUser.propTypes = {
    openCreateUser: propTypes.bool.isRequired,
    handleFab: propTypes.func,
    btnClick: propTypes.string,
    userDetails: propTypes.object,


}

export default CreateUser
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

import React, {useEffect, useState} from "react"
import {Box} from "@mui/system";
import MoreVertIcon from '@mui/icons-material/MoreVert';
import PersonIcon from '@mui/icons-material/Person';
import {Avatar, Card, CardContent, Chip, DialogTitle, Fab, Grid, IconButton, InputBase, Menu, MenuItem, ToggleButton, ToggleButtonGroup, Typography
} from "@mui/material";
import CardActions from '@mui/material/CardActions';
import axios from "axios";
import SearchIcon from '@mui/icons-material/Search';
import Divider from "@mui/material/Divider";
import Paper from "@mui/material/Paper";
import {Close} from "@mui/icons-material";
import {styled} from '@mui/material/styles';
import VisibilityIcon from "@mui/icons-material/Visibility";
import ModeIcon from "@mui/icons-material/Mode";
import DeleteIcon from "@mui/icons-material/Delete";
import ListItemIcon from "@mui/material/ListItemIcon";
import AddIcon from "@mui/icons-material/Add";
import CreateUser from "./CreateUser";
import AccountBalanceIcon from '@mui/icons-material/AccountBalance'
import DialogContent from "@mui/material/DialogContent";
import Dialog from "@mui/material/Dialog";
import DialogContentText from "@mui/material/DialogContentText";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";
import Snackbar from "@mui/material/Snackbar";
import Tooltip from "@mui/material/Tooltip";
import {SnackbarProvider} from "notistack";
import Alert from "@mui/material/Alert";


function stringToColor(string) {

    let hash = 0;
    let i;
    /* eslint-disable no-bitwise */
    for (i = 0; i < string.length; i += 1) {
        hash = string.charCodeAt(i) + ((hash << 5) - hash);
    }

    let color = '#';
    for (i = 0; i < 3; i += 1) {
        const value = (hash >> (i * 8)) & 0xff;
        color += `00${value.toString(16)}`.slice(-2);
    }
    /* eslint-enable no-bitwise */
    return color;
}

function stringAvatar(name) {
    let a = name.split(' ')[0][0];
    let b = name.split(' ')[1][0];
    return {
        sx: {
            bgcolor: stringToColor(name),
        },
        children: `${a + b}`,
    };
}

const CardContainer = styled(Card)(({theme}) => ({
    position: 'relative',
    overflow: 'visible',
    backgroundColor: 'inherit',
    boxShadow: 'none',
    //padding: theme.spacing(2),
}));

const IconButtonWrapper = styled('div')({
    position: 'absolute',
    top: 0,
    right: 0,
    padding: '8px',
});

const AvatarWrapper = styled('div')({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100%',
});

const UserModule = () => {
    document.title = 'CRS | Users';
    const [alignment, setAlignment] = useState('All');
    const [users, setUsers] = useState([]);
    const [searchUser, setSearchUser] = useState('');
    const [branchSearch, setBranchSearch] = useState('');
    const [btnClick, setBtnClick] = useState('');
    const [openCreateUser, setOpenCreateUser] = useState(false);
    const [userDetails, setUserDetails] = useState(null);
    const [pfIdForModal, setPfIdForModal] = useState('')
    const [delOpen, setDelOpen] = useState(false);
    const [delUserMsg, setDelUserMsg] = useState('');
    const [pfSearch, setPfSearch] = useState('');
    const [anchorEl, setAnchorEl] = React.useState(null);
    const [roleFilteredList, setRoleFilteredList] = useState([]);
    const [snackbar, setSnackbar] = React.useState(null);
    const open = Boolean(anchorEl);

    const handleCloseSnackbar = () => setSnackbar(null);

    useEffect(() => {
        const handleKeyPress = (e) => {
            if (e.key === "Enter") {
                e.preventDefault()
            }
        }
        document.addEventListener('keypress', handleKeyPress);
        loadUsers().then(r => {
            console.log('data fetched.');
        });
    }, [])


    const handleChange = (event, newAlignment) => {
        setAlignment(newAlignment);
    };

    const handleClickOpenCreateUser = () => {
        console.log("INSIDE MODEL");
        setOpenCreateUser(true);
        setBtnClick('create');
    };

    const handleFabClose = () => {
        setOpenCreateUser(false);
    };


    const loadUsers = async () => {
        try {
            const response =
                await axios.post("Server/userModule/getList", {},
                    {
                        headers: {
                            'Authorization': `Bearer ${localStorage.getItem('token')}`,
                        }
                    });
            console.log(response);
            setUsers(response.data.result)
            setRoleFilteredList(users)
        } catch (e) {
            console.error(e)
        }
    }
    let filter
    if (roleFilteredList.length === 0) {
        filter = users.map((row, index) => ({
            ...row,
            originalIndex: index
        })).filter(row =>
            row.FIRST_NAME.toLowerCase().includes(searchUser.toLowerCase()) ||
            row.LAST_NAME.toLowerCase().includes(searchUser.toLowerCase()) ||
            row.PF_NUMBER.toString().includes(searchUser.toString())
        );
    } else {
        filter = roleFilteredList.map((row, index) => ({
            ...row,
            originalIndex: index
        })).filter(row =>
            row.FIRST_NAME.toLowerCase().includes(searchUser.toLowerCase()) ||
            row.LAST_NAME.toLowerCase().includes(searchUser.toLowerCase()) ||
            row.PF_NUMBER.toString().includes(searchUser.toString())
        );
    }


    const handleSearchBranchChange = (e) => {
        setSearchUser(e.target.value);
    }

    const getUserListByBranch = async () => {
        if (branchSearch) {
            try {
                const response = await axios.post('/Server/userModule/getUserBranch',
                    {
                        branch: branchSearch
                    }, {
                        headers: {
                            'Authorization': `Bearer ${localStorage.getItem('token')}`,
                        }
                    });
                if (response.data.result.length !== 0) {
                    setUsers(response.data.result)
                } else {
                    setSnackbar({children: 'No user found for branch', severity: 'error'});
                }
            } catch (e) {
                console.error(e);
            }
        } else {
            setSnackbar({children: 'Enter branch code to search', severity: 'error'});
        }
    }

    const handleSearchBranchClear = (e) => {
        setSearchUser('');
    }

    const handleSearchByPf = async () => {

        /*if (pfSearch !== '')*/
        if(pfSearch){
            try {
                const response = await axios.post('Server/userModule/searchUserByPf',
                    {
                        pfNumber: pfSearch
                    }, {
                        headers: {
                            'Authorization': `Bearer ${localStorage.getItem('token')}`,
                        }
                    });
                if (response.data.message === 'User already exists'){
                    /*if (response.data.result !== null) {*/
                    let singleUser = [response.data.result];
                    setUsers(singleUser)
                }
                if (Object.keys(response.data.result).length !== 0 || response.data.result === undefined) {
                    let singleUser = [response.data.result];
                    setUsers(singleUser)
                }
                else   {
                    setSnackbar({children: 'No user found', severity: 'error'});
                }
            } catch (e) {
                console.error(e)
            }
        } else {
            setSnackbar({children: 'Select PF Number First.', severity: 'error'});
        }
    }

    const handleRoleFilter = (role) => {
        if (role === '00') {
            setRoleFilteredList(users);
        } else {
            let filteredRow = users.filter(f => (f.USER_ROLE === role));
            setRoleFilteredList(filteredRow);
        }
    }

    const handleClick = (e, value) => {
        setAnchorEl(e.currentTarget);
        setPfIdForModal(value)

    };

    const handleClose = (data) => {
        setAnchorEl(null);
    };

    const handleMenuAction = (data) => {
        let map = users.filter(f => f.PF_NUMBER === pfIdForModal);
        setAnchorEl(null);
        setOpenCreateUser(true);
        setUserDetails(map[0]);
        setBtnClick(data);
    }

    const handleDeleteAction = async (data) => {
        let map = users.filter(f => f.PF_NUMBER === pfIdForModal);
        setDelOpen(true);
        setDelUserMsg('Are you sure to delete ' + map[0].FIRST_NAME + ' ' + map[0].LAST_NAME + ' ?');
    }

    const handleConfirmDelete = () => {
        console.log("handleConfirmDelete is called...");
        try {
            const response = axios.post('/Server/userModule/deleteUser',
                {
                    pfNumber: pfIdForModal
                }, {
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('token')}`,
                    }
                });
            console.log(" delete user response:::::"+JSON.stringify(response.data));
            setDelOpen(false)
        } catch (e) {
            console.error(e)
        }
    }
    return (
        <Box sx={{display: 'flex', flexDirection: 'column'}}>
            <Grid container>
                <Typography variant={'h5'}>
                    User Module
                </Typography>
            </Grid>

            <Grid container spacing={2}>
                <Box sx={{
                    display: 'flex',
                    width: '100%',
                    //flexDirection: 'row',
                    alignItems: 'center',
                    justifyContent: 'center',
                    gap: 1,
                }}>
                    <Paper
                        style={{textAlign: 'center', width: '25%'}}
                        component="form"
                        sx={{
                            p: '2px 4px',
                            display: 'flex',
                            alignItems: 'center',
                            width: "25%"
                        }}>
                        <AccountBalanceIcon sx={{ml: 1}}/>
                        <InputBase autoFocus
                                   sx={{ml: 1, flex: 1}} placeholder="Search Branch"
                                   inputProps={{
                                       'aria-label': 'search google maps',
                                       maxLength: 5
                                   }}
                                   value={branchSearch}
                                   onChange={(e) => {
                                       if (/^\d*$/.test(e.target.value)) {
                                           setBranchSearch(e.target.value);
                                       }
                                   }}
                        />
                        <Divider sx={{height: 28, m: 1.0}} orientation="vertical"/>
                        <IconButton type="button" sx={{p: '10px'}} aria-label="clear"
                                    onClick={getUserListByBranch}>
                            <SearchIcon/>
                        </IconButton>
                    </Paper>
                    &emsp;
                    <Paper
                        style={{textAlign: 'center', width: '25%'}}
                        component="form"
                        sx={{
                            p: '2px 4px',
                            display: 'flex',
                            alignItems: 'center',
                            width: "25%"
                        }}>
                        <PersonIcon sx={{ml: 1}}/>
                        <InputBase sx={{ml: 1, flex: 1}}
                                   placeholder="Search PF Number "
                                   inputProps={{
                                       'aria-label': 'search user by pfId',
                                       maxLength: 7
                                   }}
                                   value={pfSearch}
                                   onChange={(e) => {
                                       if (/^\d*$/.test(e.target.value)) {
                                           setPfSearch(e.target.value)
                                       }
                                   }}/>
                        <Divider sx={{height: 28, m: 1.0}} orientation="vertical"/>
                        <IconButton type="button" sx={{p: '10px'}} aria-label="clear"
                                    onChange={handleSearchByPf}>
                            <SearchIcon onClick={handleSearchByPf}/>
                        </IconButton>
                    </Paper>
                </Box>
            </Grid>
            <br/>
            <Grid container spacing={0} sx={{alignItems: 'center'}}>
                <Grid item xs={1}>
                    <Typography variant={'h6'}>
                        Filter By Role:
                    </Typography>
                </Grid>
                <Grid item xs={8} sx={{justifyContent: 'center', alignItems: 'center'}}>
                    <Box>
                        <ToggleButtonGroup
                            color="primary"
                            value={alignment}
                            exclusive
                            onChange={handleChange}
                            aria-label="Platform">
                            <ToggleButton onClick={() => handleRoleFilter('00')} value="All">All</ToggleButton>
                            <ToggleButton onClick={() => handleRoleFilter('Maker')} value="Maker">Maker</ToggleButton>
                            <ToggleButton onClick={() => handleRoleFilter('Checker')}
                                          value="Checker">Checker</ToggleButton>
                            <ToggleButton onClick={() => handleRoleFilter('Auditor')}
                                          value="Auditor">Auditor</ToggleButton>
                            <ToggleButton onClick={() => handleRoleFilter('RO Manager')} value="RO Manager">RO
                                Manager</ToggleButton>
                            <ToggleButton onClick={() => handleRoleFilter('LHO Manager')} value="LHO Manager">LHO
                                Manager</ToggleButton>
                        </ToggleButtonGroup>
                    </Box>
                </Grid>

                <Grid item xs={3}>
                    <Paper style={{textAlign: 'center', width: '100%'}}
                           component="form"
                           sx={{
                               p: '2px 4px',
                               display: 'flex',
                               alignItems: 'center',
                               width: 350,
                           }}>
                        <SearchIcon sx={{ml: 1}}/>
                        <Divider sx={{height: 28, m: 1.0}} orientation="vertical"/>
                        <InputBase sx={{ml: 1, flex: 1}}
                                   placeholder=" Filter by Branch OR User "
                                   inputProps={{'aria-label': 'search branch or user'}}
                                   value={searchUser}
                                   onChange={handleSearchBranchChange}
                        />
                        <Divider sx={{height: 28, m: 1.0}} orientation="vertical"/>
                        <IconButton type="button" sx={{p: '10px'}} aria-label="clear"
                                    onClick={handleSearchBranchClear}>
                            <Close/>
                        </IconButton>
                    </Paper>
                </Grid>
            </Grid>
            <br/>
            <Divider/>
            <Box sx={{height: '70vh', overflow: 'auto', mt: 1}}>
                <Grid container spacing={2} direction='row'>
                    {filter ? (<></>) : (<p>No Users Found</p>)}
                    {filter.map((vd, index) =>
                        <Grid item key={vd.PF_NUMBER}>
                            <Card sx={{width: 280, height: 240}}>
                                <CardContent sx={{textAlign: 'center'}}>
                                    <CardContainer>
                                        <IconButtonWrapper>
                                            <IconButton value={vd.PF_NUMBER}
                                                        aria-label="settings"
                                                        onClick={(e) => handleClick(e, vd.PF_NUMBER)}
                                                        aria-controls={open ? 'basic-menu' : undefined}
                                                        aria-haspopup="true"
                                                        aria-expanded={open ? 'true' : undefined}>
                                                <MoreVertIcon/>
                                            </IconButton>
                                        </IconButtonWrapper>
                                        <CardContent>
                                            <AvatarWrapper>
                                                <Avatar variant="Large"
                                                        sx={{
                                                            alignItems: 'center',
                                                            width: 100,
                                                            height: 100,
                                                            mt: 2,
                                                            textAlign: 'center'
                                                        }}
                                                        {...stringAvatar(vd.FIRST_NAME.charAt(0).toUpperCase() + ' '
                                                            + vd.LAST_NAME.charAt(0).toUpperCase())
                                                        }
                                                />
                                            </AvatarWrapper>
                                        </CardContent>
                                    </CardContainer>
                                    <Menu
                                        elevation={1}
                                        anchorEl={anchorEl}
                                        id="account-menu"
                                        open={open}
                                        onClose={handleClose}
                                        transformOrigin={{horizontal: 'right', vertical: 'top'}}
                                        anchorOrigin={{horizontal: 'right', vertical: 'bottom'}}
                                    >
                                        <MenuItem onClick={(e) => handleMenuAction('view')}>
                                            <ListItemIcon>
                                                <VisibilityIcon fontSize="small"/>
                                            </ListItemIcon>
                                            View User
                                        </MenuItem>
                                        <Divider/>
                                        <MenuItem onClick={(e) => handleMenuAction('edit')}>
                                            <ListItemIcon>
                                                <ModeIcon fontSize="small"/>
                                            </ListItemIcon>
                                            Edit User
                                        </MenuItem>
                                        <Divider/>
                                        <MenuItem onClick={(e) => handleDeleteAction('delete')}>
                                            <ListItemIcon>
                                                <DeleteIcon fontSize="small"/>
                                            </ListItemIcon>
                                            Delete User
                                        </MenuItem>
                                    </Menu>

                                    <Typography variant="body1" color="text.secondary">
                                        {vd.PF_NUMBER}
                                    </Typography>

                                    <Tooltip title={vd.FIRST_NAME + ' ' + vd.LAST_NAME}>
                                        <Typography noWrap sx={{
                                            overflow: 'hidden',
                                            textOverflow: 'ellipsis',
                                            maxWidth: '250px'
                                        }}
                                                    style=
                                                        {{
                                                            textAlign: 'center',
                                                            fontWeight: 600
                                                        }}>
                                            {(vd.FIRST_NAME + ' ' + ' ' + ' ' + vd.LAST_NAME).toUpperCase().slice(0, 20) + ' '
                                                + ((vd.FIRST_NAME + ' ' + ' ' + ' ' + vd.LAST_NAME).toUpperCase().length > 20 ? '...' : '')}
                                        </Typography>
                                    </Tooltip>


                                    <Typography variant="body2" color="text.secondary">
                                        Branch - {vd.BRANCH_CODE}
                                    </Typography>
                                </CardContent>
                                <CardActions sx={{alignItems: 'center', justifyContent: 'center'}}>
                                    <Chip style={{width: "auto", height: 30, alignItems: 'center'}}
                                          label={vd.USER_ROLE === null ? "Role" : vd.USER_ROLE}
                                          sx={
                                              vd.USER_ROLE === "Maker" ? {
                                                      backgroundColor: '#f60a36',
                                                      color: 'white',
                                                      fontSize: 16
                                                  } :
                                                  vd.USER_ROLE === "Checker" ? {
                                                          backgroundColor: '#f65905',
                                                          color: 'white',
                                                          fontSize: 16
                                                      }
                                                      : vd.USER_ROLE === "Auditor" ? {
                                                              backgroundColor: '#0becd9',
                                                              color: 'white',
                                                              fontSize: 16
                                                          }
                                                          : vd.USER_ROLE === "RO" ? {
                                                                  backgroundColor: '#0becd9',
                                                                  color: 'white',
                                                                  fontSize: 16
                                                              }
                                                              : vd.USER_ROLE === "LHO Manager" ? {
                                                                      backgroundColor: '#008080',
                                                                      color: 'white',
                                                                      fontSize: 16
                                                                  }
                                                                  : {
                                                                      backgroundColor: '#5c1a03',
                                                                      color: 'white',
                                                                      fontSize: 16
                                                                  }}/>
                                </CardActions>
                            </Card>
                        </Grid>
                    )}

                </Grid>
                <Fab onClick={handleClickOpenCreateUser}
                     color="primary"
                     variant="extended" sx={{
                    position: "fixed",
                    borderRadius: 1,
                    fontSize: '15px',
                    bottom: (theme) => theme.spacing(10),
                    right: (theme) => theme.spacing(4)
                }}>
                    <AddIcon sx={{mr: 1}}/>
                    Create User
                </Fab>
            </Box>
            <CreateUser openCreateUser={openCreateUser} handleFab={handleFabClose} userDetails={userDetails}
                        btnClick={btnClick}/>
            <Dialog
                open={delOpen}
                onClose={() => setDelOpen(false)}
                maxWidth={"md"}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    {"Delete User"}
                </DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                        {delUserMsg}
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => {{
                        handleConfirmDelete();
                        loadUsers();          // to load user after delete action performed.
                        setSnackbar({children: 'User delete successfully.', severity: 'success'});
                        setAnchorEl(null);
                        setDelOpen(false);}}} >Confirm
                    </Button>

                    <Button
                        onClick={() => {{
                            setAnchorEl(null);
                            setDelOpen(false);
                        }}}
                        autoFocus>
                        Cancel
                    </Button>
                </DialogActions>
            </Dialog>
            {!!snackbar && (
                <SnackbarProvider maxSnack={3}>
                    <Snackbar
                        open
                        anchorOrigin={{vertical: 'bottom', horizontal: 'right'}}
                        onClose={handleCloseSnackbar}
                        autoHideDuration={3000}

                    >
                        <Alert
                            variant="filled"
                            {...snackbar} onClose={handleCloseSnackbar}/>
                    </Snackbar>
                </SnackbarProvider>
            )}
        </Box>
    )
}
export default UserModule
 
