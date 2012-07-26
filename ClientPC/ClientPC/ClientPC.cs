using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.IO;
using System.Collections;
using Microsoft.Win32;


namespace ClientPC
{
    public partial class frmClient : Form
    {
        public delegate void SEND(String str);
        public SEND sender;
        TcpClient client = new TcpClient();
        public const int PORT_NUMBER = 9999;
        
        public frmClient()
        {
            InitializeComponent();
        }

       

        public void btnConnect_Click(object sender, EventArgs e)
        {
          try {
                btnConnect.Text = "Waiting...";
	            // 1. connect
                if (txtIP.Text != "")
                {
                    client.Connect(txtIP.Text, PORT_NUMBER);
                    btnConnect.Text = "Connected";
                    SaveRegistry();
                }
                else
                {
                    MessageBox.Show("Please enter IP of Server!","",MessageBoxButtons.OK);
                }
            }
            catch (Exception ex) {
                MessageBox.Show("Error: " + ex);
                btnConnect.Text = "Disconnect";
            }
        }
        

        public void SendMessage()
        {
            
            Stream stream = client.GetStream();        
            string number = txtNo.Text;
            char[] arr = number.ToCharArray();
            int m = arr.Length;
            string str = Convert.ToString(m) + number + txtSend.Text;
            StreamWriter writer = new StreamWriter(stream);
            writer.AutoFlush = true;
            writer.WriteLine(str);
            txtMessage.AppendText(txtSend.Text+"\n");
            txtSend.Text = "";
            
        }

        private void btnSend_Click(object sender, EventArgs e)
        {
            SendMessage();
            txtSend.Text = "";
            Receiver();   
            
        }
        //Using Enter send Message
        private void txtMessage_KeyPress(object sender, KeyPressEventArgs e)
        {
            // If the key is Enter
            if (e.KeyChar == (char)13)
            {
                btnSend_Click(null,null);
            }
        }

        // Receiver Message
        public void Receiver()
        {
            Stream stream = client.GetStream();
            StreamReader reader=new StreamReader(stream);
            string str;
            string number = "";
            string No = "";
            string message = "";
            string k = "";
            while ((str = reader.ReadLine()) != null) 
            {
                //string str = reader.ReadLine();
                char[] arr = str.ToCharArray();
                int n = arr.Length;
                k += arr[0];
                if (k == "N")
                {
                    for (int i = 2; i < n; i++)
                    {
                       No += arr[i];
                    }
                    frmCall frm = new frmCall(No);
                    frm.Show();
                    
                }
                else
                {
                    int m = Convert.ToInt32(k);
                    for (int i = 0; i < n; i++)
                    {
                        if ((i > 0) && (i < n)) message += arr[i];
                    }
                    for (int i = 0; i < n; i++)
                    {
                        if ((i > 0) && (i < m + 1)) number += arr[i];
                    }
                    txtNo.Text = number;
                    txtMessage.AppendText("\n" + message + "\n");
                }
                break;
            }
            
            

        }

        // Click button Exit
        private void btnExit_Click(object sender, EventArgs e)
        {
            DialogResult dialog = MessageBox.Show("Are you sure? Your server status will be Offline!", "Exit Action Confirm", MessageBoxButtons.YesNo);
            if (dialog == DialogResult.Yes)
            {
                client.Close();
                Close();
            }
            else if (dialog == DialogResult.No)
            {
                //doing something
            }
        }

        private void SaveRegistry()
        {
            if (checkBox1.Checked == true)
            {

                Registry.SetValue(@"HKEY_CURRENT_USER\Software\SaveIP", "Chk", "1");
                Registry.SetValue(@"HKEY_CURRENT_USER\Software\SaveIP", "IP", txtIP.Text);
            }
            else if (checkBox1.Checked == false)
            {
                Registry.SetValue(@"HKEY_CURRENT_USER\Software\SaveIP", "Chk", "0");
                Registry.SetValue(@"HKEY_CURRENT_USER\Software\SaveIP", "IP", "");
                
            }
        }
        private void LoadRegistry()
        {
            txtIP.Text = (string)(Registry.GetValue(@"HKEY_CURRENT_USER\Software\SaveIP", "IP", null));
            if ((string)Registry.GetValue(@"HKEY_CURRENT_USER\Software\SaveIP", "Chk", null) == "1")
            {
                checkBox1.Checked = true;
            }
            if ((string)Registry.GetValue(@"HKEY_CURRENT_USER\Software\SaveIP", "Chk", null) == "0")
            {
                checkBox1.Checked = false;
            }
        }

        private void frmClient_Load(object sender, EventArgs e)
        {
            txtIP.Focus();
            LoadRegistry();
        }


    }
}
