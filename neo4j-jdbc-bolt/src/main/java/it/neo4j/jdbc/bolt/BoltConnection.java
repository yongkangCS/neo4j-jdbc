/**
 * Copyright (c) 2016 LARUS Business Automation [http://www.larus-ba.it]
 * <p>
 * This file is part of the "LARUS Integration Framework for Neo4j".
 * <p>
 * The "LARUS Integration Framework for Neo4j" is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Created on 17/02/16
 */
package it.neo4j.jdbc.bolt;

import it.neo4j.jdbc.Connection;
import it.neo4j.jdbc.ResultSet;
import it.neo4j.jdbc.Statement;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

/**
 * @author AgileLARUS
 * @since 3.0.0
 */
public class BoltConnection extends Connection {

	private boolean closed   = false;
	private boolean readOnly = false;

	@Override public void close() throws SQLException {
		if (!this.closed) {
			this.closed = true;
		}
	}

	@Override public boolean isClosed() throws SQLException {
		return this.closed;
	}

	@Override public void setReadOnly(boolean readOnly) throws SQLException {
		if (this.closed) {
			throw new SQLException("Connection already closed");
		}
		this.readOnly = readOnly;
	}

	@Override public boolean isReadOnly() throws SQLException {
		if (this.closed) {
			throw new SQLException("Connection already closed");
		}
		return this.readOnly;
	}

	@Override public Statement createStatement() throws SQLException {
		if (this.closed) {
			throw new SQLException("Connection already closed");
		}
		throw new UnsupportedOperationException();
	}

	@Override public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		if (this.closed) {
			throw new SQLException("Connection already closed");
		}
		// @formatter:off
		if( resultSetType != ResultSet.TYPE_FORWARD_ONLY &&
			resultSetType != ResultSet.TYPE_SCROLL_INSENSITIVE &&
			resultSetType != ResultSet.TYPE_SCROLL_SENSITIVE
		){
			throw new SQLFeatureNotSupportedException();
		}
		if( resultSetConcurrency != ResultSet.CONCUR_UPDATABLE &&
			resultSetConcurrency != ResultSet.CONCUR_READ_ONLY
		){
			throw new SQLFeatureNotSupportedException();
		}
		// @formatter:on
		throw new UnsupportedOperationException();
	}

	@Override public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		if (this.closed) {
			throw new SQLException("Connection already closed");
		}
		// @formatter:off
		if( resultSetType != ResultSet.TYPE_FORWARD_ONLY &&
			resultSetType != ResultSet.TYPE_SCROLL_INSENSITIVE &&
			resultSetType != ResultSet.TYPE_SCROLL_SENSITIVE
		){
			throw new SQLFeatureNotSupportedException();
		}
		if( resultSetConcurrency != ResultSet.CONCUR_UPDATABLE &&
			resultSetConcurrency != ResultSet.CONCUR_READ_ONLY
		){
			throw new SQLFeatureNotSupportedException();
		}
		if( resultSetHoldability != ResultSet.HOLD_CURSORS_OVER_COMMIT &&
			resultSetHoldability != ResultSet.CLOSE_CURSORS_AT_COMMIT
		){
			throw new SQLFeatureNotSupportedException();
		}
		// @formatter:on
		throw new UnsupportedOperationException();
	}
}