using System;
using System.Windows.Forms;

namespace L1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {

            //вызов нужной формы в зависимости от 
            //выбраной задачи
            Form taskForm = null;
            if (radioButton1.Checked)
                taskForm=new T1Form();
            if (radioButton2.Checked)
                taskForm = new T2Form();
            if (radioButton3.Checked)
                taskForm = new T3Form();
            if (radioButton4.Checked)
                taskForm = new T4Form();
            if (radioButton5.Checked)
                taskForm = new T5Form();

            //подписка на закрытие побочной формы
            if (taskForm != null)
            {
                taskForm.FormClosed += TaskForm_FormClosed;
                taskForm.Show();
                Hide();
            }
        }

        //при закрытии формы решения, открываем главную форму
        private void TaskForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            Show();
        }
    }
}
