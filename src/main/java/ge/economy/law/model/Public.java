/**
 * This class is generated by jOOQ
 */
package ge.economy.law.model;

import ge.economy.law.model.tables.*;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

	private static final long serialVersionUID = -1031011304;

	/**
	 * The reference instance of <code>public</code>
	 */
	public static final Public PUBLIC = new Public();

	/**
	 * No further instances allowed
	 */
	private Public() {
		super("public");
	}

	@Override
	public final List<Sequence<?>> getSequences() {
		List result = new ArrayList();
		result.addAll(getSequences0());
		return result;
	}

	private final List<Sequence<?>> getSequences0() {
		return Arrays.<Sequence<?>>asList(
			Sequences.BOARD_BOARD_ID_SEQ,
			Sequences.CASE_CASE_ID_SEQ,
			Sequences.CASE_DOC_DOC_ID_SEQ,
			Sequences.COURT_COURT_ID_SEQ,
			Sequences.COURT_INSTANCE_INSTANCE_ID_SEQ,
			Sequences.END_RESULT_END_RESULT_ID_SEQ,
			Sequences.JUDGE_JUDGE_ID_SEQ,
			Sequences.LITIGATION_SUBJECT_LITIGATION_SUBJECT_ID_SEQ,
			Sequences.STATUS_STATUS_ID_SEQ,
			Sequences.USER_STATUS_STATUS_ID_SEQ,
			Sequences.USER_TYPE_TYPE_ID_SEQ,
			Sequences.USER_USER_ID_SEQ);
	}

	@Override
	public final List<Table<?>> getTables() {
		List result = new ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final List<Table<?>> getTables0() {
		return Arrays.<Table<?>>asList(
			Board.BOARD,
			Case.CASE,
			CaseDoc.CASE_DOC,
			Court.COURT,
			CourtInstance.COURT_INSTANCE,
			EndResult.END_RESULT,
			Judge.JUDGE,
			LitigationSubject.LITIGATION_SUBJECT,
			Status.STATUS,
			User.USER,
			UserStatus.USER_STATUS,
			UserType.USER_TYPE);
	}
}
