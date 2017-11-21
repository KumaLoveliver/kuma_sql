package top.starrysea.sql;

import java.util.HashMap;
import java.util.Map;

import top.starrysea.sql.genertor.DeleteGenerator;
import top.starrysea.sql.genertor.Generator;
import top.starrysea.sql.genertor.GeneratorFactory;
import top.starrysea.sql.genertor.InsertGenerator;
import top.starrysea.sql.genertor.UpdateGenerator;

public abstract class NonQuerySqlGenerator implements ISqlGenerator {

	private static Map<Class<? extends ISqlGenerator>, Class<? extends Generator>> generatorMap = new HashMap<>();

	static {
		generatorMap.put(InsertSqlGenerator.class, InsertGenerator.class);
		generatorMap.put(UpdateSqlGenerator.class, UpdateGenerator.class);
		generatorMap.put(DeleteSqlGenerator.class, DeleteGenerator.class);
	}

	@Override
	public SqlWithParams generate() {
		ISqlGenerator sqlGenerator = getType();
		GeneratorFactory factory = new GeneratorFactory(sqlGenerator);
		Generator generator = factory.getGenerator(generatorMap.get(sqlGenerator.getClass()));
		return generator.generate(null);
	}

	protected abstract ISqlGenerator getType();

}
