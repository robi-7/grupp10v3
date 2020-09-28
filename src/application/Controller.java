package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tableModels.Table1;
import database.DataAccessLayer;

public class Controller implements Initializable {
	
	//VARIABLES
	private DataAccessLayer database;


    @FXML
    private TableView<Table1> tbl1;

    @FXML
    private TableColumn<Table1, String> tbl1Col1;

    @FXML
    private TableColumn<Table1, String> tbl1Col2;

    @FXML
    private TableColumn<Table1, String> tbl1Col3;

    @FXML
    private TableColumn<Table1, String> tbl1Col4;

    @FXML
    private TableColumn<Table1, String> tbl1Col5;
   
    @FXML
    private Button btnEmpInfo;

    @FXML
    private Button btnEmpRel;

    @FXML
    private Button btnEmpAbs;

    @FXML
    private Button btnEmpSta;

    @FXML
    private Button btnEmpQua;

    @FXML
    private Button btnEmpPor;

	//INITIALIZATOR
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tbl1Col1.setCellValueFactory(new PropertyValueFactory<Table1,String>("col1"));
		tbl1Col2.setCellValueFactory(new PropertyValueFactory<Table1,String>("col2"));
		tbl1Col3.setCellValueFactory(new PropertyValueFactory<Table1,String>("col3"));
		tbl1Col4.setCellValueFactory(new PropertyValueFactory<Table1,String>("col4"));
		tbl1Col5.setCellValueFactory(new PropertyValueFactory<Table1,String>("col5"));
		try {
			database = new DataAccessLayer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void getEmployeeInfo() {
		try {
			ResultSet rs = database.getEmployeeInfo();
			ObservableList<Table1> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new Table1(col1, col2, col3, col4, col5));
			}
			tbl1.setItems(data);
			tbl1Col1.setText("First Name");
			tbl1Col2.setText("Last Name");
			tbl1Col3.setText("Job Title");
			tbl1Col4.setText("Address");
			tbl1Col5.setText("E-Mail");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void getEmployeeRelative() {
		try {
			ResultSet rs = database.getEmployeeRelative();
			ObservableList<Table1> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new Table1(col1, col2, col3, col4, col5));
			}
			tbl1.setItems(data);	
			tbl1Col1.setText("Emp. No");
			tbl1Col2.setText("Relative Code");
			tbl1Col3.setText("First Name");
			tbl1Col4.setText("Last Name");
			tbl1Col5.setText("Birth Date");
			
		} catch (SQLException e) {
			
		}
	}

	@FXML
	public void getEmployeeAbsence() {
		try {
			ResultSet rs = database.getEmployeeAbsence();
			ObservableList<Table1> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new Table1(col1, col2, col3, col4, col5));
			}
			tbl1.setItems(data);	
			tbl1Col1.setText("Entry No");
			tbl1Col2.setText("Emp. No");
			tbl1Col3.setText("From Date");
			tbl1Col4.setText("To Date");
			tbl1Col5.setText("Description");
		} catch (SQLException e) {
			
		}
	}
	
	@FXML
	public void getEmployeeQualification() {
		try {
			ResultSet rs = database.getEmployeeQualification();
			ObservableList<Table1> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new Table1(col1, col2, col3, col4, col5));
			}
			tbl1.setItems(data);	
			tbl1Col1.setText("Emp. No");
			tbl1Col2.setText("From Date");
			tbl1Col3.setText("To Date");
			tbl1Col4.setText("Description");
			tbl1Col5.setText("Company");
		} catch (SQLException e) {
			
		}
	}
	
	@FXML
	public void getEmployeeStatsGroup() {
		try {
			ResultSet rs = database.getEmployeeStatsGroup();
			ObservableList<Table1> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = "";
				String col5 = "";
				data.add(new Table1(col1, col2, col3, col4, col5));
			}
			tbl1.setItems(data);	
			tbl1Col1.setText("Timestamp");
			tbl1Col2.setText("Code");
			tbl1Col3.setText("Description");
			tbl1Col4.setText("");
			tbl1Col5.setText("");
		} catch (SQLException e) {
			
		}
	}
	
	@FXML
	public void getEmployeePortalSetup() {
		try {
			ResultSet rs = database.getEmployeePortalSetup();
			ObservableList<Table1> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new Table1(col1, col2, col3, col4, col5));
			}
			tbl1.setItems(data);	
			tbl1Col1.setText("First Name");
			tbl1Col2.setText("Last Name");
			tbl1Col3.setText("Job Title");
			tbl1Col4.setText("Address");
			tbl1Col5.setText("E-Mail");
		} catch (SQLException e) {
			
		}
	}


}
