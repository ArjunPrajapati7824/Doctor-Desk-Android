package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorPrescriptionBinding;
//
import com.example.doctordesk.doctor.Model.MedicineDetails;
import com.example.doctordesk.utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
//import com.itextpdf.layout.properties.TextAlignment;
//import com.itextpdf.layout.properties.UnitValue;
//import com.itextpdf.layout.property.TextAlignment;
//import com.itextpdf.layout.property.UnitValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DoctorPrescription extends AppCompatActivity {

    private ActivityDoctorPrescriptionBinding binding;

    String[] bloodGroup = {"A+", "A-", "B+", "B-","AB+", "AB-","O+","O-"};

    ArrayList<String> arrayList;

    AutoCompleteTextView autoComplete;

    private FirebaseFirestore firestore;;

    EditText Name, number, age, BG, temp, Bs, spo2, pulse, Bp, allergy, cc, Finding, Advice, Mname;
    Button CreatePre,DeleteMedicine;
    android.widget.RadioGroup RadioGroup;

    private LinearLayout Layout;
    Button AddMedicine;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorPrescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        arrayList=new ArrayList<>();
        arrayList.add("A+");
        arrayList.add("A-");
        arrayList.add("B+");
        arrayList.add("B-");
        arrayList.add("AB+");
        arrayList.add("AB-");
        arrayList.add("O+");
        arrayList.add("O-");
        //add items in bloodgroup spinner
        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,bloodGroup);
        binding.SpinnerBloodGroup.setAdapter(bloodGroupAdapter);


        binding.SpinnerBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                binding.SpinnerBloodGroup.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        Name = findViewById(R.id.DName);
//        number = findViewById(R.id.PhoneNumber);
//        age = findViewById(R.id.PatientAge);
//        BG = findViewById(R.id.BloodGroup);
//        CreatePre = findViewById(R.id.CreatePrescription);
//        AddMedicine = findViewById(R.id.AddMedicine);
//        Layout = findViewById(R.id.LayoutList);
//        DeleteMedicine=findViewById(R.id.DeleteMedicine);
//
//        temp = findViewById(R.id.tempreture);
//        Bs = findViewById(R.id.Sugar);
//        spo2 = findViewById(R.id.Oxy);
//        pulse = findViewById(R.id.Pulse);
//        Bp = findViewById(R.id.BloodPressure);
//        allergy = findViewById(R.id.Allergy);
//        cc = findViewById(R.id.ChiefComp);
//        Finding = findViewById(R.id.Finding);
//        Advice = findViewById(R.id.Advice);

        String appointment_name,appointment_number,appointment_age,appointment_gender,BloodGroup;
        appointment_name= getIntent().getStringExtra("Appointment_name");
        appointment_number=getIntent().getStringExtra("Appointment_phone");
        appointment_age=getIntent().getStringExtra("Appointment_age");
        appointment_gender=getIntent().getStringExtra("Appointment_gender");


        Date currentDate = new Date();

// Create a SimpleDateFormat object to format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

// Format the date as a string
        String formattedDate = dateFormat.format(currentDate);


        binding.DName.setText(appointment_name);
        binding.PhoneNumber.setText(appointment_number);
        binding.PatientAge.setText(appointment_age);
        binding.Gender.setText(appointment_gender);
        binding.DateTime.setText(formattedDate);



        binding.CreatePrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get data from user In String
                String name = binding.DName.getText().toString();
                String Phonenumber = binding.PhoneNumber.getText().toString();
                String Age = binding.PatientAge.getText().toString();
                String bg =  binding.SpinnerBloodGroup.getSelectedItem().toString();
                String tempp = binding.tempreture.getText().toString();
                String bs = binding.Sugar.getText().toString();
                String spO2 = binding.Oxy.getText().toString();
                String bp = binding.BloodPressure.getText().toString();
                String pul = binding.Pulse.getText().toString();
                String allargy = binding.Allergy.getText().toString();
                String CC = binding.ChiefComp.getText().toString();
                String finding = binding.Finding.getText().toString();
                String advice = binding.Advice.getText().toString();
                String gender=binding.Gender.getText().toString();
                String date=binding.DateTime.getText().toString();
                // Aa Gender RadioButton MA Error aave 6e pela thai gyu tu have nai Thatu joi leje tu

             /*   int Id=RadioGroup.getCheckedRadioButtonId();
                Gender=findViewById(Id);
                String gen=Gender.getText().toString();*/
                if(name.equals("")){
                    Toast.makeText(DoctorPrescription.this, "Name not  define", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        //On Click Calling The Create Pdf Method and Passing All The Detail in as parameter
                        CreatePrescription(name, Phonenumber, Age, bg, tempp, bs, spO2, bp, pul, allargy, CC, finding, advice ,gender,date);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });


        binding.AddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        }); //Add Multiple view Of Medicine Detail
    }


    private void addView()
    {
        //Getting Fdata from layout if Medicine Detail
        View Medicine=getLayoutInflater().inflate(R.layout.medicine_details_row,null,false) ;

//        getLayoutInflater().inflate(R.layout.medicine_details_row,null,false) ;

        EditText Mname=(EditText)Medicine.findViewById(R.id.Mname);
        EditText Tqty=(EditText)Medicine.findViewById(R.id.Tqty);
        EditText Frequency=(EditText)Medicine.findViewById(R.id.Frequency);
        EditText Rout=(EditText)Medicine.findViewById(R.id.Rout);
        EditText Days=(EditText)Medicine.findViewById(R.id.Days);
        EditText instruction=(EditText)Medicine.findViewById(R.id.instruction);
        Button DeleteMedicine=(Button)Medicine.findViewById(R.id.DeleteMedicine);

        autoComplete=Medicine.findViewById(R.id.AutocompleteView);
        ArrayList<String> arrautocomplete=new ArrayList<>();


        firestore = FirebaseFirestore.getInstance();

        firestore.collection(Constants.KEY_COLLECTION_MEDICINES).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            arrautocomplete.clear();
                            for (DocumentSnapshot document : task.getResult()) {
                                // Assuming you have a field called "value" in each document
                                String value = document.getString(Constants.KEY_MEDICINE_NAME);
                                arrautocomplete.add(value);
                            }

//                            AdapterAutocomplete.notifyDataSetChanged();
                        }
                        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrautocomplete);
                        autoComplete.setAdapter(adapter);
                        autoComplete.setThreshold(1);

                    }
                });

        //Delete MEdicine Button To delete Nany added Medicine
        DeleteMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(Medicine);
            }
        });
        binding.LayoutList.addView(Medicine);
    }
    private  void removeView(View v){
        binding.LayoutList.removeView(v);

    }

    //Create Pdf method
    private void CreatePrescription(String name, String Phonenumber, String Age, String bg, String tempp, String bs,//pass All The paramerte
                                    String spO2, String bp, String pul,
                                    String allargy, String CC, String finding,
                                    String advice,String gender,String date) throws FileNotFoundException {


        // Java Methods To Create Pdf File
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfpath, name + ".pdf");
        OutputStream op = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pd = new PdfDocument(writer);
        Document document = new Document(pd);
// Existing code for creating PDF document
        DeviceRgb color = new DeviceRgb(16, 107, 213);//Color code For Heading
        float columnWidth[] = {400, 400};//Table Size For the doctor name and clinic name Table
        Table table1 = new Table(columnWidth);

        // 1st , belove Three are cell for The doctor Name and Clinic detail
        table1.addCell(new Cell().add(new Paragraph("Clinic_Name" + "\n" +getIntent().getStringExtra("Clinic_Name")).setFontSize(26f).setFontColor(color)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Dr. " +getIntent().getStringExtra("Doctor_name")).setFontSize(26f).setFontColor(color)).setBorder(Border.NO_BORDER));


        // 2nd
        table1.addCell(new Cell().add(new Paragraph("Address : " + getIntent().getStringExtra("Clinic_Address"))).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Specilization: " +getIntent().getStringExtra("Specialization"))).setBorder(Border.NO_BORDER));

        // 3rd
        table1.addCell(new Cell().add(new Paragraph("Contact No. " +getIntent().getStringExtra("Doctor_contact"))).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Registration No : " +getIntent().getStringExtra("registration_number"))).setBorder(Border.NO_BORDER));

        LineSeparator line = new LineSeparator(new SolidLine());


        float columnWidth2[] = {400, 400};
        Table table2 = new Table(columnWidth);

        table2.addCell(new Cell().add(new Paragraph("Patient Name:  " + name)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("Age:  " + Age)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().add(new Paragraph("Gender:  " + gender)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("Contact No.  " + Phonenumber)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().add(new Paragraph("Blood Group:  " + bg)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("Blood Sugar:  " + bs)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().add(new Paragraph("Pulse:  " + pul)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("Blood Pressure:  " + bp)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().add(new Paragraph("Temperature:  " + tempp)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("Spo2:  " + spO2)).setBorder(Border.NO_BORDER));



        Paragraph Allerg = new Paragraph("Allergy").setBold().setFontSize(15f);
        Paragraph Allerg1 = new Paragraph(allargy);

        Paragraph cc = new Paragraph("Chief Complain").setBold().setFontSize(15f);
        Paragraph Cc = new Paragraph(CC);

        Paragraph Finding = new Paragraph("Findings").setBold().setFontSize(15f);
        Paragraph Finding1 = new Paragraph(finding);

        Paragraph Advice = new Paragraph("Advice").setBold().setFontSize(15f);
        Paragraph Advice1 = new Paragraph(advice);
        Paragraph m = new Paragraph("Medicine Details").setBold().setFontSize(15f);

//Adding Data To The PDF
        document.add(table1);
        document.add(line);
//        document.add(Pdetail);
        document.add(table2);
        document.add(Allerg);
        document.add(Allerg1);
        document.add(cc);
        document.add(Cc);
        document.add(Finding);
        document.add(Finding1);
        document.add(Advice);
        document.add(Advice1);
        document.add(m);


        // Get all medicine details from the layout
        List<MedicineDetails> medicineList = new ArrayList<>();
        for (int i = 0; i < binding.LayoutList.getChildCount(); i++) {
            View child = binding.LayoutList.getChildAt(i);
            EditText medicineNameEditText = child.findViewById(R.id.AutocompleteView);
            EditText quantityEditText = child.findViewById(R.id.Tqty);
            EditText frequencyEditText = child.findViewById(R.id.Frequency);
            EditText routeEditText = child.findViewById(R.id.Rout);
            EditText daysEditText = child.findViewById(R.id.Days);
            EditText instructionEditText = child.findViewById(R.id.instruction);

            String medicineName = medicineNameEditText.getText().toString();
            String quantity = quantityEditText.getText().toString();
            String frequency = frequencyEditText.getText().toString();
            String route = routeEditText.getText().toString();
            String days = daysEditText.getText().toString();
            String instruction = instructionEditText.getText().toString();

            MedicineDetails medicine = new MedicineDetails(medicineName, quantity, frequency, route, days, instruction);
            medicineList.add(medicine);
        }

        // Create a table for displaying medicine details
        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();
        table.addHeaderCell("Medicine Name");
        table.addHeaderCell("Quantity");
        table.addHeaderCell("Frequency");
        table.addHeaderCell("Route");
        table.addHeaderCell("Days");
        table.addHeaderCell("Instruction");

        for (MedicineDetails medicine : medicineList) {

//For Each Loop to Add The Detail From the Layout To Pdf
            table.addCell(new Cell().add(new Paragraph(medicine.getMedicineName())));
            table.addCell(new Cell().add(new Paragraph(medicine.getQuantity())));
            table.addCell(new Cell().add(new Paragraph(medicine.getFrequency())));
            table.addCell(new Cell().add(new Paragraph(medicine.getRoute())));
            table.addCell(new Cell().add(new Paragraph(medicine.getDays())));
            table.addCell(new Cell().add(new Paragraph(medicine.getInstruction())));
        }
        document.add(table);


        // Close the document
        document.close();

        Toast.makeText(this, "Prescription created successfully!", Toast.LENGTH_SHORT).show();
    }


}