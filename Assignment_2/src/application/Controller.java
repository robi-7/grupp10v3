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
import tableModels.TableData;
import tableModels.TableMetadata;
import database.DataAccessLayer;

public class Controller implements Initializable {
	
	//VARIABLES
	private DataAccessLayer database;


    @FXML
    private TableView<TableData> tblData;

    @FXML
    private TableColumn<TableData, String> tblDataCol1;

    @FXML
    private TableColumn<TableData, String> tblDataCol2;

    @FXML
    private TableColumn<TableData, String> tblDataCol3;

    @FXML
    private TableColumn<TableData, String> tblDataCol4;

    @FXML
    private TableColumn<TableData, String> tblDataCol5;
   
    @FXML
    private Button btnDataEmpInfo;

    @FXML
    private Button btnDataEmpRel;

    @FXML
    private Button btnDataEmpAbs;

    @FXML
    private Button btnDataEmpSta;

    @FXML
    private Button btnDataEmpQua;

    @FXML
    private Button btnDataEmpPor;
    // TAB METADATA
    @FXML
    private Button btnMetadataEmpCol;

    @FXML
    private Button btnMetadataConstraints;

    @FXML
    private Button btnMetadataEmpMD;

    @FXML
    private Button btnMetadataMaxRows;

    @FXML
    private Button btnMetadataKeys;

    @FXML
    private Button btnMetadataTables;
    
    @FXML
    private TableView<TableMetadata> tblMetadata;

    @FXML
    private TableColumn<TableMetadata,String> tblMetadataCol1;

    @FXML
    private TableColumn<TableMetadata,String> tblMetadataCol2;

    @FXML
    private TableColumn<TableMetadata,String> tblMetadataCol3;

    @FXML
    private TableColumn<TableMetadata,String> tblMetadataCol4;
	//INITIALIZATOR
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tblDataCol1.setCellValueFactory(new PropertyValueFactory<TableData,String>("col1"));
		tblDataCol2.setCellValueFactory(new PropertyValueFactory<TableData,String>("col2"));
		tblDataCol3.setCellValueFactory(new PropertyValueFactory<TableData,String>("col3"));
		tblDataCol4.setCellValueFactory(new PropertyValueFactory<TableData,String>("col4"));
		tblDataCol5.setCellValueFactory(new PropertyValueFactory<TableData,String>("col5"));
		
		tblMetadataCol1.setCellValueFactory(new PropertyValueFactory<TableMetadata,String>("col1"));
		tblMetadataCol2.setCellValueFactory(new PropertyValueFactory<TableMetadata,String>("col2"));
		tblMetadataCol3.setCellValueFactory(new PropertyValueFactory<TableMetadata,String>("col3"));
		tblMetadataCol4.setCellValueFactory(new PropertyValueFactory<TableMetadata,String>("col4"));
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
			ObservableList<TableData> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new TableData(col1, col2, col3, col4, col5));
			}
			tblData.setItems(data);
			tblDataCol1.setText("First Name");
			tblDataCol2.setText("Last Name");
			tblDataCol3.setText("Job Title");
			tblDataCol4.setText("Address");
			tblDataCol5.setText("E-Mail");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void getEmployeeRelative() {
		try {
			ResultSet rs = database.getEmployeeRelative();
			ObservableList<TableData> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new TableData(col1, col2, col3, col4, col5));
			}
			tblData.setItems(data);	
			tblDataCol1.setText("Emp. No");
			tblDataCol2.setText("Relative Code");
			tblDataCol3.setText("First Name");
			tblDataCol4.setText("Last Name");
			tblDataCol5.setText("Birth Date");
			
		} catch (SQLException e) {
			
		}
	}

	@FXML
	public void getEmployeeAbsence() {
		try {
			ResultSet rs = database.getEmployeeAbsence();
			ObservableList<TableData> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new TableData(col1, col2, col3, col4, col5));
			}
			tblData.setItems(data);	
			tblDataCol1.setText("Entry No");
			tblDataCol2.setText("Emp. No");
			tblDataCol3.setText("From Date");
			tblDataCol4.setText("To Date");
			tblDataCol5.setText("Description");
		} catch (SQLException e) {
			
		}
	}
	
	@FXML
	public void getEmployeeQualification() {
		try {
			ResultSet rs = database.getEmployeeQualification();
			ObservableList<TableData> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new TableData(col1, col2, col3, col4, col5));
			}
			tblData.setItems(data);	
			tblDataCol1.setText("Emp. No");
			tblDataCol2.setText("From Date");
			tblDataCol3.setText("To Date");
			tblDataCol4.setText("Description");
			tblDataCol5.setText("Company");
		} catch (SQLException e) {
			
		}
	}
	
	@FXML
	public void getEmployeeStatsGroup() {
		try {
			ResultSet rs = database.getEmployeeStatsGroup();
			ObservableList<TableData> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);				
				String col4 = "";
				String col5 = "";
				data.add(new TableData(col1, col2, col3, col4, col5));
			}
			tblData.setItems(data);	
			tblDataCol1.setText("Timestamp");
			tblDataCol2.setText("Code");
			tblDataCol3.setText("Description");
			tblDataCol4.setText("");
			tblDataCol5.setText("");
		} catch (SQLException e) {
			
		}
	}
	
	@FXML
	public void getEmployeePortalSetup() {
		try {
			ResultSet rs = database.getEmployeePortalSetup();
			ObservableList<TableData> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				String col5 = rs.getString(5);
				data.add(new TableData(col1, col2, col3, col4, col5));
			}
			tblData.setItems(data);	
			tblDataCol1.setText("First Name");
			tblDataCol2.setText("Last Name");
			tblDataCol3.setText("Job Title");
			tblDataCol4.setText("Address");
			tblDataCol5.setText("E-Mail");
		} catch (SQLException e) {
			
		}
	}

	
	
	
	
	@FXML
	public void getKeys() {
		try {
			ResultSet rs = database.getKeys();
			ObservableList<TableMetadata> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = "";
				String col3 = "";
				String col4 = "";
				
				data.add(new TableMetadata(col1, col2, col3, col4));
			}
			tblMetadata.setItems(data);
			tblMetadataCol1.setText("Column Name");
			tblMetadataCol2.setText("");
			tblMetadataCol3.setText("");
			tblMetadataCol4.setText("");
			double maxWidth = tblMetadata.getPrefWidth();
			tblMetadataCol1.setPrefWidth(maxWidth);
			tblMetadataCol2.setPrefWidth(0);
			tblMetadataCol3.setPrefWidth(0);
			tblMetadataCol4.setPrefWidth(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void getConstraints() {
		try {
			ResultSet rs = database.getConstraints();
			ObservableList<TableMetadata> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = "";
				String col3 = "";
				String col4 = "";
				
				data.add(new TableMetadata(col1, col2, col3, col4));
			}
			tblMetadata.setItems(data);
			tblMetadataCol1.setText("Constraint Name");
			tblMetadataCol2.setText("");
			tblMetadataCol3.setText("");
			tblMetadataCol4.setText("");
			double maxWidth = tblMetadata.getPrefWidth();
			tblMetadataCol1.setPrefWidth(maxWidth);
			tblMetadataCol2.setPrefWidth(0);
			tblMetadataCol3.setPrefWidth(0);
			tblMetadataCol4.setPrefWidth(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void getTables() {
		try {
			ResultSet rs = database.getTables();
			ObservableList<TableMetadata> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = "";
				String col3 = "";
				String col4 = "";
				
				data.add(new TableMetadata(col1, col2, col3, col4));
			}
			tblMetadata.setItems(data);
			tblMetadataCol1.setText("Table Name");
			tblMetadataCol2.setText("");
			tblMetadataCol3.setText("");
			tblMetadataCol4.setText("");
			double maxWidth = tblMetadata.getPrefWidth();
			tblMetadataCol1.setPrefWidth(maxWidth);
			tblMetadataCol2.setPrefWidth(0);
			tblMetadataCol3.setPrefWidth(0);
			tblMetadataCol4.setPrefWidth(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void getEmployeeColumns() {
		try {
			ResultSet rs = database.getEmployeeColumns();
			ObservableList<TableMetadata> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = "";
				String col3 = "";
				String col4 = "";
				
				data.add(new TableMetadata(col1, col2, col3, col4));
			}
			tblMetadata.setItems(data);
			tblMetadataCol1.setText("Column Name");
			tblMetadataCol2.setText("");
			tblMetadataCol3.setText("");
			tblMetadataCol4.setText("");
			double maxWidth = tblMetadata.getPrefWidth();
			tblMetadataCol1.setPrefWidth(maxWidth);
			tblMetadataCol2.setPrefWidth(0);
			tblMetadataCol3.setPrefWidth(0);
			tblMetadataCol4.setPrefWidth(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void getEmployeeMetadata() {
		try {
			ResultSet rs = database.getEmployeeMetadata();
			ObservableList<TableMetadata> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				String col4 = rs.getString(4);
				
				data.add(new TableMetadata(col1, col2, col3, col4));
			}
			tblMetadata.setItems(data);
			tblMetadataCol1.setText("Catalog");
			tblMetadataCol2.setText("Schema");
			tblMetadataCol3.setText("Name");
			tblMetadataCol4.setText("Type");
			double maxWidth = tblMetadata.getPrefWidth();
			tblMetadataCol1.setPrefWidth(maxWidth/4);
			tblMetadataCol2.setPrefWidth(maxWidth/4);
			tblMetadataCol3.setPrefWidth(maxWidth/4);
			tblMetadataCol4.setPrefWidth(maxWidth/4);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void getMaxRowsTable() {
		try {
			ResultSet rs = database.getMaxRowsTable();
			ObservableList<TableMetadata> data = FXCollections.observableArrayList();
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = "";
				String col3 = "";
				String col4 = "";
				
				data.add(new TableMetadata(col1, col2, col3, col4));
			}
			tblMetadata.setItems(data);
			tblMetadataCol1.setText("Table Name");
			tblMetadataCol2.setText("");
			tblMetadataCol3.setText("");
			tblMetadataCol4.setText("");
			double maxWidth = tblMetadata.getPrefWidth();
			tblMetadataCol1.setPrefWidth(maxWidth);
			tblMetadataCol2.setPrefWidth(0);
			tblMetadataCol3.setPrefWidth(0);
			tblMetadataCol4.setPrefWidth(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
