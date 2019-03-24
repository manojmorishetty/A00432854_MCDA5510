using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Mail;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;
using Data_Access;

namespace Assignment3
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            CreateLogFiles();
            formLoad();
        }
        private int Counter = 0;
        private string PostalCoderegex = "[ABCEGHJKLMNPRSTVXY][0-9][ABCEGHJKLMNPRSTVWXYZ] ?[0-9][ABCEGHJKLMNPRSTVWXYZ][0-9]";
        private string PhoneNoregex = @"\(?\d{3}\)?-? *\d{3}-? *-?\d{4}";
        private string emailregex = @"^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$";


        private string sLogFormat;
        private string sErrorTime;
        private string sPathName = "../../../Error.log";

        public void CreateLogFiles()
        {
            //sLogFormat used to create log files format :
            // dd/mm/yyyy hh:mm:ss AM/PM ==> Log Message
            sLogFormat = DateTime.Now.ToShortDateString().ToString() + " " + DateTime.Now.ToLongTimeString().ToString() + " ==> ";

            //this variable used to create log filename format "
            //for example filename : ErrorLogYYYYMMDD
            string sYear = DateTime.Now.Year.ToString();
            string sMonth = DateTime.Now.Month.ToString();
            string sDay = DateTime.Now.Day.ToString();
            sErrorTime = sYear + sMonth + sDay;
        }

        public void ErrorLog(string sErrMsg)
        {
            StreamWriter sw = new StreamWriter(sPathName + sErrorTime, true);
            sw.WriteLine(sLogFormat + sErrMsg);
            sw.Flush();
            sw.Close();
        }

        private bool checkRegex(string text,string regex, string errorType)
        {
            Match mc = Regex.Match(text, regex);
            if (!mc.Success)
            {
                ErrorLog("Error in "+ errorType+": " +text);
            }
            return mc.Success;
            
        }

        private void getStatus()
        {
            textStatus.Text = "";
            String[] errorList =new String[3] { textEmail.Text, textPhoneNo.Text, textPostalCode.Text };
            String[] erroron = new String[3] { "Email", "Phone Number", "Postal Code" };

            for (int i = 0; i < errorList.Length; i++)
            {
                if (errorList[i] == "Error")
                {
                    textStatus.Text = "Error in "+ erroron[i];
                    break;
                }
            }
            
        }

        
        public void getResults(List<person> p)
        {
            PID.Text = p.ElementAt(Counter).pid.ToString();
            textFirstname.Text = p.ElementAt(Counter).FName;
            textLastname.Text = p.ElementAt(Counter).LName;
            textStreetNumber.Text = p.ElementAt(Counter).Street_Number;
            textstreet.Text = p.ElementAt(Counter).Street;
            textCity.Text = p.ElementAt(Counter).City;
            textProvince.Text = p.ElementAt(Counter).Province;
            textCountry.Text = p.ElementAt(Counter).Country;
            textPostalCode.Text = checkRegex(p.ElementAt(Counter).Postal_Code, PostalCoderegex,"Postal Code") ? p.ElementAt(Counter).Postal_Code : "Error";
            textPhoneNo.Text = checkRegex(p.ElementAt(Counter).Phone_Number.ToString(), PhoneNoregex,"Phone Number") ? p.ElementAt(Counter).Phone_Number.ToString() : "Error";
            textEmail.Text = checkRegex(p.ElementAt(Counter).email, emailregex,"Email") ? p.ElementAt(Counter).email : "Error";
            getStatus();
        }

        private void Previous_Click(object sender, EventArgs e)
        {
            List<person> p = new List<person>();
            p = getPersonControllerInstance().getAllPersons();
            if (Counter>0)
            {
                Counter--;
                getResults(p);
            }
        }

        private void formLoad()
        {
            List<person> p = new List<person>();
            p = getPersonControllerInstance().getAllPersons();
            getResults(p);

        }

        private void Next_Click(object sender, EventArgs e)
        {
            List<person> p = new List<person>();
            p = getPersonControllerInstance().getAllPersons();
            if (Counter < p.Count-1)
            {
                Counter++;
                getResults(p);
            }
            
        }

        private PersonController getPersonControllerInstance()
        {
            return new PersonController();
        }
    }
}
