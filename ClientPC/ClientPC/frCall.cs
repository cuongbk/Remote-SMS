using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ClientPC
{
    public partial class frmCall : Form
    {
       
        public frmCall(string str)
		{
			InitializeComponent();
			lblNo.Text=str;
		}


        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
       
    }
}
