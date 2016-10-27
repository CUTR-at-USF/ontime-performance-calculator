/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usf.cutr.OPC;

/** Why a GTFS feed failed to load */
public enum LoadStatus {
	SUCCESS, INVALID_ZIP_FILE, OTHER_FAILURE, MISSING_REQUIRED_FIELD, INCORRECT_FIELD_COUNT_IMPROPER_QUOTING;
}
