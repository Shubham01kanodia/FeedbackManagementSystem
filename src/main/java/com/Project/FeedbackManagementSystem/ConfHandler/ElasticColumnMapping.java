package com.Project.FeedbackManagementSystem.ConfHandler;

import java.util.Map;

public class ElasticColumnMapping {
	String indexName;
	String documentName;
	String primaryField;
	Map<String, String> columnsList;
	

	public ElasticColumnMapping(String indexName, String documentName, String primaryField, Map<String, String> columnsList) {
		this.indexName = indexName;
		this.documentName = documentName;
		this.columnsList = columnsList;
		this.primaryField = primaryField;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public Map<String, String> getColumnsList() {
		return columnsList;
	}
	public void setColumnsList(Map<String, String> columnsList) {
		this.columnsList = columnsList;
	}
	public String getPrimaryField() {
		return primaryField;
	}
	public void setPrimaryField(String primaryField) {
		this.primaryField = primaryField;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnsList == null) ? 0 : columnsList.hashCode());
		result = prime * result + ((documentName == null) ? 0 : documentName.hashCode());
		result = prime * result + ((indexName == null) ? 0 : indexName.hashCode());
		result = prime * result + ((primaryField == null) ? 0 : primaryField.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElasticColumnMapping other = (ElasticColumnMapping) obj;
		if (columnsList == null) {
			if (other.columnsList != null)
				return false;
		} else if (!columnsList.equals(other.columnsList))
			return false;
		if (documentName == null) {
			if (other.documentName != null)
				return false;
		} else if (!documentName.equals(other.documentName))
			return false;
		if (indexName == null) {
			if (other.indexName != null)
				return false;
		} else if (!indexName.equals(other.indexName))
			return false;
		if (primaryField == null) {
			if (other.primaryField != null)
				return false;
		} else if (!primaryField.equals(other.primaryField))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ElasticColumnMapping [indexName=" + indexName + ", documentName=" + documentName + ", primaryField="
				+ primaryField + ", columnsList=" + columnsList + "]";
	}
	
	
	
		
}
