package abstractfactory;

interface ComputeInstance {
    void start();
}

interface BlobStorage {
    void save(String name);
}

class AwsCompute implements ComputeInstance {
    @Override
    public void start() {
        System.out.println("[AWS] Вычислительный узел запущен");
    }
}

class AwsBlobStorage implements BlobStorage {
    @Override
    public void save(String name) {
        System.out.println("[AWS] Файл сохранён: " + name);
    }
}

class AzureCompute implements ComputeInstance {
    @Override
    public void start() {
        System.out.println("[AZURE] Вычислительный узел запущен");
    }
}

class AzureBlobStorage implements BlobStorage {
    @Override
    public void save(String name) {
        System.out.println("[AZURE] Файл сохранён: " + name);
    }
}

interface CloudFactory {
    ComputeInstance createCompute();

    BlobStorage createStorage();
}

class AwsFactory implements CloudFactory {
    @Override
    public ComputeInstance createCompute() {
        return new AwsCompute();
    }

    @Override
    public BlobStorage createStorage() {
        return new AwsBlobStorage();
    }
}

class AzureFactory implements CloudFactory {
    @Override
    public ComputeInstance createCompute() {
        return new AzureCompute();
    }

    @Override
    public BlobStorage createStorage() {
        return new AzureBlobStorage();
    }
}

class DeploymentManager {
    private final ComputeInstance compute;
    private final BlobStorage storage;

    public DeploymentManager(CloudFactory factory) {
        compute = factory.createCompute();
        storage = factory.createStorage();
    }

    public void deploy(String fileName) {
        compute.start();
        storage.save(fileName);
    }
}

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Укажите провайдера: aws или azure");
            return;
        }

        CloudFactory factory;

        switch (args[0].toLowerCase(java.util.Locale.ROOT)) {
            case "aws":
                factory = new AwsFactory();
                break;
            case "azure":
                factory = new AzureFactory();
                break;
            default:
                System.out.println("Неизвестный провайдер: " + args[0]);
                return;
        }

        DeploymentManager manager = new DeploymentManager(factory);
        manager.deploy("application.jar");
    }
}