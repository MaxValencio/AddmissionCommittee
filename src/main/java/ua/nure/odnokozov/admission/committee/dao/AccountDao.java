package ua.nure.odnokozov.admission.committee.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.admission.committee.entity.Account;
import ua.nure.odnokozov.admission.committee.entity.Language;
import ua.nure.odnokozov.admission.committee.entity.builder.AccountBuilder;
import ua.nure.odnokozov.admission.committee.enums.ActivationStatus;
import ua.nure.odnokozov.admission.committee.enums.Role;

public class AccountDao extends AbstractDao<Account> {

    private static final Logger LOG = Logger.getLogger(AccountDao.class);

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD_HASH = "password_hash";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_ACTIVATION_STATUS = "activation_status";
    private static final String COLUMN_REGISTRATION_DATE = "registration_date";
    private static final String COLUMN_LANGUAGE_LABEL = "label";
    private static final String COLUMN_LANGUAGE_ID = "language_id";

    private static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT accounts.id, accounts.email, accounts.password_hash, accounts.role, accounts.activation_status, "
            + "accounts.registration_date, languages.language_id, languages.label FROM accounts "
            + "INNER JOIN languages ON accounts.language_id = languages.language_id "
            + "WHERE accounts.id = ? LIMIT 1;";

    private static final String SQL_SELECT_ALL_ACCOUNTS = "{call select_all_accounts()}";

    private static final String SQL_INSERT_ACCOUNT = "{call insert_and_select_created_account(?, ?, ?)}";

    private static final String SQL_UPDATE_ACCOUNT = "{call update_and_select_updated_account(?, ?, ?, ?, ?)}";

    private static final String SQL_DELETE_ACCOUNT = "DELETE FROM accounts WHERE id= ?";

    @Override
    public Account getById(long id) {
        return getById(SQL_SELECT_ACCOUNT_BY_ID, ps -> ps.setLong(1, id), getMapper());
    }

    private EntityMapper<Account> getMapper() {
        return resultSet -> {
            return AccountBuilder.getInstance().id(resultSet.getLong(COLUMN_ID))
                        .email(resultSet.getString(COLUMN_EMAIL))
                        .passwordHash(resultSet.getString(COLUMN_PASSWORD_HASH))
                        .role(Role.valueOf(resultSet.getString(COLUMN_ROLE)))
                        .activationStatus(ActivationStatus.valueOf(resultSet.getString(COLUMN_ACTIVATION_STATUS)))
                        .registrationDate(
                                Timestamp.valueOf(resultSet.getString(COLUMN_REGISTRATION_DATE)).toLocalDateTime())
                        .language(new Language(resultSet.getLong(COLUMN_LANGUAGE_ID),
                                resultSet.getString(COLUMN_LANGUAGE_LABEL)))
                        .build();
        };
    }

    @Override
    public List<Account> getAll() {
        LOG.debug("Get all users");
        return getAll(SQL_SELECT_ALL_ACCOUNTS, getMapper());
    }

    @Override
    public Account create(Account entity) {
        LOG.debug("Create account: " + entity);
        return createUpdate(SQL_INSERT_ACCOUNT, ps -> {
            int count = 1;
            ps.setString(count++, entity.getEmail());
            ps.setString(count++, entity.getPasswordHash());
            ps.setLong(count, entity.getLanguage().getId());
        }, getMapper());
    }

    @Override
    public Account update(Account entity) {
        LOG.debug("Update user: " + entity);
        return createUpdate(SQL_UPDATE_ACCOUNT, ps -> {
            int count = 1;
            ps.setString(count++, entity.getEmail());
            ps.setString(count++, entity.getPasswordHash());
            ps.setString(count++, entity.getActivationStatus().getName());
            ps.setLong(count++, entity.getLanguage().getId());
            ps.setLong(count, entity.getId());
        }, getMapper());
    }

    @Override
    public boolean delete(long id) {
        LOG.debug("Delete account where id = " + id);
        return delete(SQL_DELETE_ACCOUNT, ps -> {
            ps.setLong(1, id);
        });
    }
}
